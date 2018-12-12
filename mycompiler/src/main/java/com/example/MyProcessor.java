package com.example;

import com.example.annotations.MyBindView;
import com.example.annotations.MyOnClick;
import com.google.auto.service.AutoService;

import java.util.LinkedHashSet;
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
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

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
        Set<String> types = new LinkedHashSet<>();

        //需要全类名
        types.add(MyBindView.class.getCanonicalName());
        types.add(MyOnClick.class.getCanonicalName());
        return types;
    }

    // 2、确认支持哪种jdk的版本
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    // 会被处理器调用，可以在这里获取Filer，Elements，Messager等辅助类，后面会解释
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);

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
        // 返回指定给定注解的元素。
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(MyBindView.class);
        for (Element element : elements) {
            // 判断注解类型
            if (element.getKind() == ElementKind.CLASS) {
                TypeElement typeElement = (TypeElement) element;

                Name name = typeElement.getSimpleName(); // 元素名称
                int value = typeElement.getAnnotation(MyBindView.class).value(); // 属性值
                System.out.println("name=" + name + "   value=" + value);
            }
        }

        return false;
    }
}
