package com.tahlia.apt_annotation;

import com.squareup.javapoet.MethodSpec;
import com.tahlia.annotation.CountTime;
import com.tahlia.annotation.HelloWorld;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.tools.Diagnostic;

@SupportedAnnotationTypes({"A", "B", "C"})
public class MyProcessor extends AbstractProcessor {

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(CountTime.class.getCanonicalName());
        types.add(HelloWorld.class.getCanonicalName());
        return types;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, annotation.getSimpleName());
            if (annotation.asType())
        }
        checkMethod(roundEnv);
        return false;
    }

    private void checkMethod(RoundEnvironment env) {
        Set<? extends Element> elementsAnnotatedWith = env.getElementsAnnotatedWith(CountTime.class);
        for (Element element : elementsAnnotatedWith) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, element.getSimpleName());
        }
    }


}
