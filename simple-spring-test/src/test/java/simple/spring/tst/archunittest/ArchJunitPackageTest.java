package simple.spring.tst.archunittest;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import simple.spring.tst.App;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(packagesOf = App.class, importOptions = {ImportOption.DoNotIncludeTests.class})
public class ArchJunitPackageTest {

    // Rule A: 모든 클래스는 study 또는 member 또는 domain 패키지에 속해야 한다.
    @ArchTest
    ArchRule myRule = classes()
            .should().onlyBeAccessed().byClassesThat()
            .resideInAnyPackage("..study..", "..member..", "..domain..");

    // Rule B: 모든 domain 패키지의 클래스는 study 또는 member 패키지에 접근해서는 안된다.
    @ArchTest
    ArchRule memberPackageRule = noClasses().that().resideInAPackage("..domain..")
            .should().accessClassesThat().resideInAPackage("..member..")
            .andShould().accessClassesThat().resideInAPackage("..study..");

    // Rule C: 모든 member 패키지의 클래스는 study 패키지 참조할 수 없다.
    @ArchTest
    ArchRule memberPackageRule2 = noClasses().that().resideInAPackage("..member..")
            .should().accessClassesThat().resideInAPackage("..study..");

    // Rule D: 순환참조 허용하지 않는다.
    @ArchTest
    ArchRule cycleRule = slices().matching("simple.spring.tst.(*)..")
            .should().beFreeOfCycles();

}