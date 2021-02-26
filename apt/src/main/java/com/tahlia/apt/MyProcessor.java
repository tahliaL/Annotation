package com.tahlia.apt;

import com.tahlia.annotation.BindView;
import com.tahlia.annotation.BindViewApt;
import com.tahlia.annotation.HelloWorld;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;


public class MyProcessor extends AbstractProcessor {

    private Filer mFiler;
    private Messager mMessager;
    private Elements mElementUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mFiler = processingEnv.getFiler();
        mMessager = processingEnv.getMessager();
        mElementUtils = processingEnv.getElementUtils();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(HelloWorld.class.getCanonicalName());
        return types;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> bindViewElements = roundEnv.getElementsAnnotatedWith(HelloWorld.class);
        for (Element element : bindViewElements) {
/*
            mMessager.printMessage(Diagnostic.Kind.NOTE, element.getSimpleName());
            mMessager.printMessage(Diagnostic.Kind.NOTE, element.getEnclosingElement().getSimpleName());
            mMessager.printMessage(Diagnostic.Kind.NOTE, element.getEnclosingElement().getEnclosingElement().getSimpleName());
            mMessager.printMessage(Diagnostic.Kind.NOTE, element.asType().toString());
            mMessager.printMessage(Diagnostic.Kind.NOTE, element.asType().getKind().toString());
            mMessager.printMessage(Diagnostic.Kind.NOTE, String.valueOf(element.asType().getKind().isPrimitive()));
            mMessager.printMessage(Diagnostic.Kind.NOTE, element.getKind().toString());
            mMessager.printMessage(Diagnostic.Kind.NOTE, element.getModifiers().toString());
            mMessager.printMessage(Diagnostic.Kind.NOTE, mElementUtils.getPackageOf(element).getSimpleName());
            mMessager.printMessage(Diagnostic.Kind.NOTE, mElementUtils.getPackageOf(element).getQualifiedName());
*/

/*
            mMessager.printMessage(Diagnostic.Kind.NOTE, "===================");
            // 判断是FIELD类型
            if (element.getKind().isField()) {
                // 强转
                VariableElement variableElement = (VariableElement) element;
                mMessager.printMessage(Diagnostic.Kind.NOTE, "Annotatated Element : " +variableElement.getSimpleName());
                // 获取父Element
                Element subElement = variableElement.getEnclosingElement();
                // 判断是CLASS类型
                if (subElement.getKind() == ElementKind.CLASS) {
                    TypeElement typeElement = (TypeElement) subElement;
                    mMessager.printMessage(Diagnostic.Kind.NOTE, "Sub Element : " +typeElement.getSimpleName());

                    List<? extends Element> childElements = typeElement.getEnclosedElements();
                    for (Element ele : childElements) {
                        // 判断是METHOD类型
                        if (ele.getKind() == ElementKind.METHOD) {
                            ExecutableElement methodElement = (ExecutableElement) ele;
                            mMessager.printMessage(Diagnostic.Kind.NOTE, "Method Element : "+methodElement.getSimpleName());
                        }
                    }

                }
            }
            mMessager.printMessage(Diagnostic.Kind.NOTE, "===================");
*/
/*
            StringBuilder builder = new StringBuilder();
            TypeElement typeElement = null;
            builder.append("===================\n");
            if (element.getKind().isField()) {
                VariableElement variableElement = (VariableElement) element;
                builder.append("Annotatated Element : ").append(variableElement.getSimpleName()).append("\n");
                Element subElement = variableElement.getEnclosingElement();
                if (subElement.getKind() == ElementKind.CLASS) {
                    typeElement = (TypeElement) subElement;
                    builder.append("Sub Element : ").append(typeElement.getSimpleName()).append("\n");

                    List<? extends Element> childElements = typeElement.getEnclosedElements();
                    for (Element ele : childElements) {
                        if (ele.getKind() == ElementKind.METHOD) {
                            ExecutableElement methodElement = (ExecutableElement) ele;
                            builder.append("Method Element : ").append(methodElement.getSimpleName()).append("\n");
                        }
                    }
                }
            }

            builder.append("===================\n");
            if (typeElement != null) {
                try {
                    JavaFileObject fileObject = mFiler.createSourceFile("com.tahlia.annotation.HelloWorldTest", typeElement);
                    Writer writer = fileObject.openWriter();
                    writer.write(builder.toString());
                    writer.flush();
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
*/
        }
        return false;
    }
}
