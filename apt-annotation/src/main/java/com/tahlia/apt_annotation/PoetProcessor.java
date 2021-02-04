package com.tahlia.apt_annotation;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;
import com.tahlia.annotation.BindView;
import com.tahlia.annotation.CountTime;
import com.tahlia.annotation.HelloWorld;

import java.io.File;
import java.io.IOException;
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
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.tools.Diagnostic;
public class PoetProcessor extends AbstractProcessor {

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(CountTime.class.getCanonicalName());
        types.add(HelloWorld.class.getCanonicalName());
        types.add(BindView.class.getCanonicalName());
        return types;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, annotation.getSimpleName());
        }
        checkMethodTime(roundEnv);
        newMethod(roundEnv);
        BindViewApt(roundEnv);
        newType(roundEnv);

        return true;
    }

    private void checkMethodTime(RoundEnvironment environment) {
        Set<? extends Element> countTimeElementSets = environment.getElementsAnnotatedWith(CountTime.class);
        if (countTimeElementSets.isEmpty()) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "No methods is annotationed with @CountTime");
            return;
        }
        for (Element element : countTimeElementSets) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, element.getSimpleName());
        }
    }

    private void newMethod(RoundEnvironment environment) {
        Set<? extends Element> helloWorldElementSets = environment.getElementsAnnotatedWith(HelloWorld.class);
        if (helloWorldElementSets.isEmpty()) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "No methods is annotationed with @HelloWorld");
            return;
        }
        /*for (Element element : helloWorldElementSets) {
            MethodSpec mehtod = MethodSpec.methodBuilder("HelloWorld")
                    .addModifiers(Modifier.PUBLIC)
                    .addStatement("Log.d(\"lys\", \"HelloWorld!\")")
                    .build();
        }*/
    }

    private void BindViewApt(RoundEnvironment environment) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "===================");
        Set<? extends Element> elementsAnnotatedWith = environment.getElementsAnnotatedWith(BindView.class);
        if (elementsAnnotatedWith.isEmpty()) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "No Field is annotationed with @BindView");
            return;
        }

        for (Element element : elementsAnnotatedWith) {
            String activityName = element.getEnclosingElement().getSimpleName().toString() + "$Binding";
            ClassName activity = ClassName.get("android.app", "Activity");
            ClassName viewClz = ClassName.get("android.view", "View");
            ParameterSpec parameterSpec = ParameterSpec.builder(activity, "activity").build();

            // findViewById ok
            BindView view = element.getAnnotation(BindView.class);
            int value = view.value();


            MethodSpec methodSpec = MethodSpec.methodBuilder("BindView")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .returns(void.class)
                    .addParameter(parameterSpec)
                    .addStatement("$T view = activity.findViewById($L)", viewClz, value)
                    .build();

            TypeSpec typeSpec = TypeSpec.classBuilder(activityName)
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(methodSpec)
                    .build();


            JavaFile file = JavaFile.builder("com.tahlia.annotation", typeSpec).build();
            try {
                file.writeTo(processingEnv.getFiler());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "=================");
    }


    private void newType(RoundEnvironment environment) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "1234");
        ClassName log = ClassName.get("android.util", "Log");
        MethodSpec mehtod = MethodSpec.methodBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC)
                .addStatement("$T.d(\"lys\", \"HelloWorld!\")", log)
//                .addStatement("Log.d(\"lys\", \"HelloWorld!\")")
                .returns(void.class)
                .build();

        TypeSpec type = TypeSpec.classBuilder("HelloWorldPoet")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(mehtod)
                .build();

        JavaFile file = JavaFile.builder("com.tahlia.annotation", type).build();
        try {
////            String path = "/data/data/com.tahlia.annotation";
////            file.writeTo(new File(path));
            file.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println(file.toString());
//        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, file.toString());


    }





}
