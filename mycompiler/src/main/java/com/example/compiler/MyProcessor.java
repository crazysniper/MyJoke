package com.example.compiler;

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
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * Created by Gao on 2018/11/21.
 */
@AutoService(Processor.class)
public class MyProcessor extends AbstractProcessor {

    private Filer mFiler; //文件相关的辅助类
    private Elements mElementUtils; //元素相关的辅助类
    private Messager mMessager; //日志相关的辅助类

    // 这个方法返回stirng类型的set集合，集合里包含了你需要处理的注解
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        //需要全类名
//        types.add(Seriable.class.getCanonicalName());
//        types.add(Println.class.getCanonicalName());
        return types;
//        return super.getSupportedAnnotationTypes();
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

//        elementUtils = processingEnv.getElementUtils();
//        typeUtils = processingEnv.getTypeUtils();
//        filer = processingEnv.getFiler();
    }

    // 3、对注解进行处理。相当于每个处理器的主函数main()，可以在这里写相关的扫描和处理注解的代码，他会帮助生成相关的Java文件。
    // 这个一般的流程就是先扫描查找注解，再生成 java 文件
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return false;
    }
}
