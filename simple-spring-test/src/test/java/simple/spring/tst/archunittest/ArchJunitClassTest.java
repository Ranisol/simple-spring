package simple.spring.tst.archunittest;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import simple.spring.tst.App;

import javax.persistence.Entity;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(packagesOf = App.class, importOptions = {ImportOption.DoNotIncludeTests.class})
public class ArchJunitClassTest {

    // Rule A: 모든 클래스는 study 또는 member 또는 domain 패키지에 속해야 한다.
    @ArchTest
    ArchRule controllerClassRule = classes().that()
            .haveSimpleName("Controller").should().accessClassesThat().haveSimpleNameEndingWith("Service")
            .orShould().accessClassesThat().haveSimpleNameEndingWith("Repository");

    @ArchTest
    ArchRule repositoryClassRule = classes().that()
            .haveSimpleNameEndingWith("Repository").should().accessClassesThat().haveSimpleNameEndingWith("Service");

    @ArchTest
    ArchRule studyClassesRule = classes().that()
            .haveSimpleNameStartingWith("Study").and().areNotEnums().and().areNotAnnotatedWith(Entity.class)
            .should().accessClassesThat().resideInAPackage("..study..");
}