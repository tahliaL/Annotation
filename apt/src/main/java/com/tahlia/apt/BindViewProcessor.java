package com.tahlia.apt;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.tahlia.annotation.BindView;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

public class BindViewProcessor extends AbstractProcessor {

    private Filer mFiler;
    private Messager mMessager;
    private Elements mElementUtils;

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(BindView.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFiler = processingEnv.getFiler();
        mMessager = processingEnv.getMessager();
        mElementUtils = processingEnv.getElementUtils();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // javapoet:
        String currentClzName = "";
        TypeSpec.Builder typeBuilder = null;
        MethodSpec.Builder methodBuilder = null;
        String pkgName = "";
        String activiyName = "";

        Set<? extends Element> bindViewElements = roundEnv.getElementsAnnotatedWith(BindView.class);
        for (Element element : bindViewElements) {
            BindView bindViewAnnotation = element.getAnnotation(BindView.class);
            int value = bindViewAnnotation.value();

            // 2. 利用javapoet生成类：
            pkgName = mElementUtils.getPackageOf(element).toString();
            activiyName = element.getEnclosingElement().getSimpleName().toString();

            if (!activiyName.equalsIgnoreCase(currentClzName)) {
                if (!currentClzName.isEmpty()) {
                    generateAptFile(pkgName, typeBuilder, methodBuilder);
                }
                currentClzName = activiyName;
                ClassName activity = ClassName.get(pkgName, activiyName);

                methodBuilder = MethodSpec.methodBuilder("bind")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .returns(TypeName.VOID)
                        .addParameter(ParameterSpec.builder(activity, "activity").build())
                        .addStatement("activity.$N = activity.findViewById($L)", element.getSimpleName(), value);

                typeBuilder = TypeSpec.classBuilder(currentClzName+"_ViewBinder");

            } else {
                if (methodBuilder != null) {
                    methodBuilder.addStatement("activity.$N = activity.findViewById($L)", element.getSimpleName(), value);
                }
            }
        }


        generateAptFile(pkgName, typeBuilder, methodBuilder);
        return false;
    }

    private void generateAptFile(String pkgName, TypeSpec.Builder typeBuilder, MethodSpec.Builder methodBuilder) {
        if (typeBuilder != null && methodBuilder != null) {
            typeBuilder.addMethod(methodBuilder.build());
            JavaFile javaFile = JavaFile.builder(pkgName, typeBuilder.build()).build();
            try {
                javaFile.writeTo(mFiler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
