package simple.spring.tst.archunittest;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

public class ArchUnitPackageTest {
    @Test
    @Disabled
    @DisplayName("사용 예시")
    public void Services_should_only_be_accessed_by_Controllers() {
        // 1. 특정 패키지에 있는 클래스 가져옴
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.mycompany.myapp");

        // 2. 확인할 규칙 셋업
        ArchRule myRule = classes()
                .that().resideInAPackage("..service..")
                .should().onlyBeAccessed().byAnyPackage("..controller..", "..service..");

        // 3. 규칙 따르는지 확인
        myRule.check(importedClasses);
    }

    @Test
    void packageDependencyTest() {
        JavaClasses importedClasses = new ClassFileImporter()
                .withImportOption(new ImportOption.DoNotIncludeTests())
                .importPackages("simple.spring.tst");

        // Rule A: 모든 클래스는 study 또는 member 또는 domain 패키지에 속해야 한다.
        ArchRule myRule = classes()
                .should().onlyBeAccessed().byClassesThat()
                .resideInAnyPackage("..study..", "..member..", "..domain..");

        myRule.check(importedClasses);

        // Rule B: 모든 domain 패키지의 클래스는 study 또는 member 패키지에 접근해서는 안된다.
        ArchRule memberPackageRule = noClasses().that().resideInAPackage("..domain..")
                .should().accessClassesThat().resideInAPackage("..member..")
                .andShould().accessClassesThat().resideInAPackage("..study..");
        memberPackageRule.check(importedClasses);

        // Rule C: 모든 member 패키지의 클래스는 study 패키지 참조할 수 없다.
        ArchRule studyPackageRule = noClasses().that().resideInAPackage("..member..")
                .should().accessClassesThat().resideInAPackage("..study..");
        studyPackageRule.check(importedClasses);

        // Rule D: 순환참조 허용하지 않는다.
        ArchRule cycleRule = slices().matching("simple.spring.tst.(*)..")
                .should().beFreeOfCycles();
        cycleRule.check(importedClasses);
    }

}
