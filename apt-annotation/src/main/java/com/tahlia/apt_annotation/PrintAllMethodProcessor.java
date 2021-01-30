package com.tahlia.apt_annotation;

import com.tahlia.annotation.AllMethod;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

public class PrintAllMethodProcessor extends AbstractProcessor {

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    // 第一个参数表示要处理的注解集合，因为可能会在一个processor中处理多个注解，可能内部需要区分
    // 第二个参数表示可以访问到的所有类型节点
    // 注意区分apt中使用的是element，而反射用的是clz
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Messager messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE, "================================ " + annotations.size() + "===");

        Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(AllMethod.class);

        for (Element element : elementsAnnotatedWith) {
            if (element.getKind() == ElementKind.CLASS) {
                TypeElement typeElement = (TypeElement) element;
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, typeElement.getQualifiedName().toString());
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, typeElement.getSimpleName().toString());
                messager.printMessage(Diagnostic.Kind.NOTE, "============");
                List<? extends Element> enclosedElements = typeElement.getEnclosedElements();
                for (Element ele : enclosedElements) {
                    if (ele.getKind() == ElementKind.METHOD) {
                        messager.printMessage(Diagnostic.Kind.NOTE, ele.getSimpleName());
                    }
                }
            }
        }
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(AllMethod.class.getCanonicalName());
        return types;
    }
}
