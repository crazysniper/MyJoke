package com.example;

import com.example.annotations.MyBindView;
import com.example.annotations.MyOnClick;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import static com.google.auto.common.MoreElements.getPackage;

/**
 * Created by Gao on 2018/11/21.
 */
@AutoService(Processor.class)
public class MyProcessor extends AbstractProcessor {

    private Filer filer; //文件相关的辅助类。它提供API来编写生成的源代码文件。

    private Types types; //用来处理TypeMirror的工具

    private Elements elementUtils; //元素相关的辅助类。它提供了utils方法，用于过滤处理器中不同类型的元素。
    private Messager messager; //日志相关的辅助类。用于在编译时打印消息。
    // 我们发送可能通过Messager处理的错误消息。
    // 由于注释处理器在其自己的独立环境中运行，因此我们无法通过任何其他方式与应用程序通信。

    // 这个方法返回stirng类型的set集合，集合里包含了你需要处理的注解
    // 列出了我们在处理应用程序的Java文件时要查询的注释。
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        System.out.println("MyProcessor getSupportedAnnotationTypes");
        Set<String> types = new LinkedHashSet<>();

        //需要全类名
        types.add(MyBindView.class.getCanonicalName());
        types.add(MyOnClick.class.getCanonicalName());
        return types;
    }

    // 2、确认支持哪种jdk的版本
    @Override
    public SourceVersion getSupportedSourceVersion() {
        System.out.println("MyProcessor getSupportedSourceVersion");
        return SourceVersion.latestSupported();
    }

    // 会被处理器调用，可以在这里获取Filer，Elements，Messager等辅助类，后面会解释
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

        System.out.println("MyProcessor init");

        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
        elementUtils = processingEnv.getElementUtils();
        types = processingEnv.getTypeUtils();
    }

    // 3、对注解进行处理。相当于每个处理器的主函数main()，可以在这里写相关的扫描和处理注解的代码，他会帮助生成相关的Java文件。
    // 这个一般的流程就是先扫描查找注解，再生成 java 文件
    // 调用此方法来处理应用程序的源代码。

    /**
     * @param annotations 它提供注释列表作为正在处理的Java文件中包含的元素。
     * @param roundEnv    它提供对处理环境的访问，其中包含查询元素的工具。
     *                    我们将在这个环境中使用的两个主要功能是：
     *                    processingOver（它的最后一轮处理）和getRootElements（它提供了一个将被处理的元素列表。
     *                    这些元素中将包含一些我们感兴趣的注释。）
     * @return true: 这些注解已经声明并且不要求后续Processor处理他们。
     * false:这些注解未声明并且可能要求后续Processor处理他们。
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("MyProcessor process annotations=" + annotations);
        if (annotations != null && annotations.size() != 0) {

            // 返回指定给定注解的元素。
            Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(MyBindView.class); // 拿到所有RouteAnnotation注解标注的类

            System.out.println("MyProcessor process elements=" + elements);

            String packageName = null;
            TypeSpec.Builder builder = null;

            for (Element element : elements) {
                // 判断注解类型
                System.out.println("MyProcessor process element=" + element);
                System.out.println("MyProcessor process filer=" + filer);
                System.out.println("MyProcessor process element.getKind()=" + element.getKind());
                if (element.getKind() == ElementKind.FIELD) {
                    VariableElement variableElement = (VariableElement) element;
                    TypeElement enclosingElement = (TypeElement) variableElement.getEnclosingElement();


                    // 回此类型元素的完全限定名称。更准确地说，返回规范 名称。对于没有规范名称的局部类和匿名类，返回一个空名称.
                    // 一般类型的名称不包括对其形式类型参数的任何引用。例如，接口 java.util.Set<E> 的完全限定名称是 "java.util.Set"
                    Name qualifiedName = enclosingElement.getQualifiedName();
                    Name simpleName = variableElement.getSimpleName();

                    packageName = getPackage(enclosingElement).getQualifiedName().toString();

                    String className = enclosingElement.getQualifiedName().toString().substring(
                            packageName.length() + 1).replace('.', '$');

                    ClassName bindingClassName = ClassName.get(packageName, className + "_MyViewBinding");

                    Name name = variableElement.getSimpleName(); // 元素名称
                    int value = variableElement.getAnnotation(MyBindView.class).value(); // 属性值
                    System.out.println("qualifiedName=" + qualifiedName + "name=" + name + "   value=" + value);
                    System.out.println("packageName=" + packageName + "className=" + className + "   bindingClassName=" + bindingClassName);


                    MethodSpec main = MethodSpec.methodBuilder(simpleName.toString())
                            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                            .returns(void.class)
                            .addParameter(String[].class, "args")
                            .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                            .build();

                    if (builder == null) {
                        builder = TypeSpec.classBuilder(bindingClassName)
                                .addModifiers(Modifier.PUBLIC, Modifier.FINAL);
                    }
                    builder.addMethod(main);
                }
            }
            if (builder != null) {
                TypeSpec helloWorld = builder.build();
                JavaFile javaFile = JavaFile.builder(packageName, helloWorld).build();
                try {
                    javaFile.writeTo(filer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
        return false;
    }


    private void generateJavaFile(Map<String, String> nameMap) {
        //generate constructor
        MethodSpec.Builder constructorBuidler = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addStatement("routeMap = new $T<>()", HashMap.class);
        for (String key : nameMap.keySet()) {
            String name = nameMap.get(key);
            constructorBuidler.addStatement("routeMap.put(\"$N\", \"$N\")", key, name);
        }
        MethodSpec constructorName = constructorBuidler.build();

        //generate getActivityRouteName method
        MethodSpec routeName = MethodSpec.methodBuilder("getActivityName")
                .addModifiers(Modifier.PUBLIC)
                .returns(String.class)
                .addParameter(String.class, "routeName")
                .beginControlFlow("if (null != routeMap && !routeMap.isEmpty())")
                .addStatement("return (String)routeMap.get(routeName)")
                .endControlFlow()
                .addStatement("return \"\"")
                .build();

        //generate class
//        TypeSpec typeSpec = TypeSpec.classBuilder("AnnotationRoute$Finder")
//                .addModifiers(Modifier.PUBLIC)
//                .addMethod(constructorName)
//                .addMethod(routeName)
//                .addSuperinterface(Provider.class)
//                .addField(HashMap.class, "routeMap", Modifier.PRIVATE)
//                .build();
//
//
//        JavaFile javaFile = JavaFile.builder("com.example.juexingzhe.annotaioncompiletest", typeSpec).build();
//        try {
//            javaFile.writeTo(mFiler);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


}
