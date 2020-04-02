/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.checkers;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TargetBackend;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("compiler/testData/diagnostics/testsWithJvmBackend")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class DiagnosticsTestWithJvmIrBackendGenerated extends AbstractDiagnosticsTestWithJvmIrBackend {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, TargetBackend.JVM_IR, testDataFilePath);
    }

    public void testAllFilesPresentInTestsWithJvmBackend() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/testData/diagnostics/testsWithJvmBackend"), Pattern.compile("^(.+)\\.kt$"), null, TargetBackend.JVM_IR, true);
    }

    @TestMetadata("indirectInlineCycle.kt")
    public void testIndirectInlineCycle() throws Exception {
        runTest("compiler/testData/diagnostics/testsWithJvmBackend/indirectInlineCycle.kt");
    }

    @TestMetadata("inlineCycle.kt")
    public void testInlineCycle() throws Exception {
        runTest("compiler/testData/diagnostics/testsWithJvmBackend/inlineCycle.kt");
    }

    @TestMetadata("suspendInlineCycle.kt")
    public void testSuspendInlineCycle() throws Exception {
        runTest("compiler/testData/diagnostics/testsWithJvmBackend/suspendInlineCycle.kt");
    }

    @TestMetadata("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature")
    @TestDataPath("$PROJECT_ROOT")
    @RunWith(JUnit3RunnerWithInners.class)
    public static class DuplicateJvmSignature extends AbstractDiagnosticsTestWithJvmIrBackend {
        private void runTest(String testDataFilePath) throws Exception {
            KotlinTestUtils.runTest(this::doTest, TargetBackend.JVM_IR, testDataFilePath);
        }

        public void testAllFilesPresentInDuplicateJvmSignature() throws Exception {
            KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature"), Pattern.compile("^(.+)\\.kt$"), null, TargetBackend.JVM_IR, true);
        }

        @TestMetadata("caseInProperties.kt")
        public void testCaseInProperties() throws Exception {
            runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/caseInProperties.kt");
        }

        @TestMetadata("vararg.kt")
        public void testVararg() throws Exception {
            runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/vararg.kt");
        }

        @TestMetadata("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/accidentalOverrides")
        @TestDataPath("$PROJECT_ROOT")
        @RunWith(JUnit3RunnerWithInners.class)
        public static class AccidentalOverrides extends AbstractDiagnosticsTestWithJvmIrBackend {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest(this::doTest, TargetBackend.JVM_IR, testDataFilePath);
            }

            @TestMetadata("accidentalOverrideFromGrandparent.kt")
            public void testAccidentalOverrideFromGrandparent() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/accidentalOverrides/accidentalOverrideFromGrandparent.kt");
            }

            public void testAllFilesPresentInAccidentalOverrides() throws Exception {
                KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/accidentalOverrides"), Pattern.compile("^(.+)\\.kt$"), null, TargetBackend.JVM_IR, true);
            }

            @TestMetadata("classFunctionOverriddenByProperty.kt")
            public void testClassFunctionOverriddenByProperty() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/accidentalOverrides/classFunctionOverriddenByProperty.kt");
            }

            @TestMetadata("classFunctionOverriddenByPropertyInConstructor.kt")
            public void testClassFunctionOverriddenByPropertyInConstructor() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/accidentalOverrides/classFunctionOverriddenByPropertyInConstructor.kt");
            }

            @TestMetadata("classFunctionOverriddenByPropertyNoGetter.kt")
            public void testClassFunctionOverriddenByPropertyNoGetter() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/accidentalOverrides/classFunctionOverriddenByPropertyNoGetter.kt");
            }

            @TestMetadata("classPropertyOverriddenByFunction.kt")
            public void testClassPropertyOverriddenByFunction() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/accidentalOverrides/classPropertyOverriddenByFunction.kt");
            }

            @TestMetadata("defaultFunction.kt")
            public void testDefaultFunction() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/accidentalOverrides/defaultFunction.kt");
            }

            @TestMetadata("delegatedFunctionOverriddenByProperty_ir.kt")
            public void testDelegatedFunctionOverriddenByProperty_ir() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/accidentalOverrides/delegatedFunctionOverriddenByProperty_ir.kt");
            }

            @TestMetadata("genericClassFunction.kt")
            public void testGenericClassFunction() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/accidentalOverrides/genericClassFunction.kt");
            }

            @TestMetadata("overridesNothing_ir.kt")
            public void testOverridesNothing_ir() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/accidentalOverrides/overridesNothing_ir.kt");
            }

            @TestMetadata("overridesNothing_old.kt")
            public void testOverridesNothing_old() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/accidentalOverrides/overridesNothing_old.kt");
            }

            @TestMetadata("privateClassFunctionOverriddenByProperty.kt")
            public void testPrivateClassFunctionOverriddenByProperty() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/accidentalOverrides/privateClassFunctionOverriddenByProperty.kt");
            }

            @TestMetadata("traitFunctionOverriddenByPropertyNoImpl.kt")
            public void testTraitFunctionOverriddenByPropertyNoImpl() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/accidentalOverrides/traitFunctionOverriddenByPropertyNoImpl.kt");
            }

            @TestMetadata("traitFunctionOverriddenByProperty_ir.kt")
            public void testTraitFunctionOverriddenByProperty_ir() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/accidentalOverrides/traitFunctionOverriddenByProperty_ir.kt");
            }

            @TestMetadata("traitPropertyOverriddenByFunctionNoImpl.kt")
            public void testTraitPropertyOverriddenByFunctionNoImpl() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/accidentalOverrides/traitPropertyOverriddenByFunctionNoImpl.kt");
            }

            @TestMetadata("traitPropertyOverriddenByFunction_ir.kt")
            public void testTraitPropertyOverriddenByFunction_ir() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/accidentalOverrides/traitPropertyOverriddenByFunction_ir.kt");
            }
        }

        @TestMetadata("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/bridges")
        @TestDataPath("$PROJECT_ROOT")
        @RunWith(JUnit3RunnerWithInners.class)
        public static class Bridges extends AbstractDiagnosticsTestWithJvmIrBackend {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest(this::doTest, TargetBackend.JVM_IR, testDataFilePath);
            }

            public void testAllFilesPresentInBridges() throws Exception {
                KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/bridges"), Pattern.compile("^(.+)\\.kt$"), null, TargetBackend.JVM_IR, true);
            }

            @TestMetadata("class_ir.kt")
            public void testClass_ir() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/bridges/class_ir.kt");
            }

            @TestMetadata("fakeOverrideTrait_ir.kt")
            public void testFakeOverrideTrait_ir() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/bridges/fakeOverrideTrait_ir.kt");
            }

            @TestMetadata("trait_ir.kt")
            public void testTrait_ir() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/bridges/trait_ir.kt");
            }
        }

        @TestMetadata("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/erasure")
        @TestDataPath("$PROJECT_ROOT")
        @RunWith(JUnit3RunnerWithInners.class)
        public static class Erasure extends AbstractDiagnosticsTestWithJvmIrBackend {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest(this::doTest, TargetBackend.JVM_IR, testDataFilePath);
            }

            public void testAllFilesPresentInErasure() throws Exception {
                KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/erasure"), Pattern.compile("^(.+)\\.kt$"), null, TargetBackend.JVM_IR, true);
            }

            @TestMetadata("clashFromInterfaceAndSuperClass_ir.kt")
            public void testClashFromInterfaceAndSuperClass_ir() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/erasure/clashFromInterfaceAndSuperClass_ir.kt");
            }

            @TestMetadata("collections.kt")
            public void testCollections() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/erasure/collections.kt");
            }

            @TestMetadata("delegateToTwoTraits_ir.kt")
            public void testDelegateToTwoTraits_ir() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/erasure/delegateToTwoTraits_ir.kt");
            }

            @TestMetadata("delegationAndOwnMethod_ir.kt")
            public void testDelegationAndOwnMethod_ir() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/erasure/delegationAndOwnMethod_ir.kt");
            }

            @TestMetadata("delegationToTraitImplAndOwnMethod_ir.kt")
            public void testDelegationToTraitImplAndOwnMethod_ir() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/erasure/delegationToTraitImplAndOwnMethod_ir.kt");
            }

            @TestMetadata("extensionProperties.kt")
            public void testExtensionProperties() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/erasure/extensionProperties.kt");
            }

            @TestMetadata("genericType.kt")
            public void testGenericType() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/erasure/genericType.kt");
            }

            @TestMetadata("inheritFromTwoTraits_ir.kt")
            public void testInheritFromTwoTraits_ir() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/erasure/inheritFromTwoTraits_ir.kt");
            }

            @TestMetadata("kotlinAndJavaCollections.kt")
            public void testKotlinAndJavaCollections() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/erasure/kotlinAndJavaCollections.kt");
            }

            @TestMetadata("nullableType.kt")
            public void testNullableType() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/erasure/nullableType.kt");
            }

            @TestMetadata("superTraitAndDelegationToTraitImpl_ir.kt")
            public void testSuperTraitAndDelegationToTraitImpl_ir() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/erasure/superTraitAndDelegationToTraitImpl_ir.kt");
            }

            @TestMetadata("twoTraitsAndOwnFunction_ir.kt")
            public void testTwoTraitsAndOwnFunction_ir() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/erasure/twoTraitsAndOwnFunction_ir.kt");
            }

            @TestMetadata("typeMappedToJava.kt")
            public void testTypeMappedToJava() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/erasure/typeMappedToJava.kt");
            }

            @TestMetadata("typeParameter.kt")
            public void testTypeParameter() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/erasure/typeParameter.kt");
            }

            @TestMetadata("typeParameterWithBound.kt")
            public void testTypeParameterWithBound() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/erasure/typeParameterWithBound.kt");
            }

            @TestMetadata("typeParameterWithTwoBounds.kt")
            public void testTypeParameterWithTwoBounds() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/erasure/typeParameterWithTwoBounds.kt");
            }

            @TestMetadata("typeParameterWithTwoBoundsInWhere.kt")
            public void testTypeParameterWithTwoBoundsInWhere() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/erasure/typeParameterWithTwoBoundsInWhere.kt");
            }
        }

        @TestMetadata("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/finalMembersFromBuiltIns")
        @TestDataPath("$PROJECT_ROOT")
        @RunWith(JUnit3RunnerWithInners.class)
        public static class FinalMembersFromBuiltIns extends AbstractDiagnosticsTestWithJvmIrBackend {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest(this::doTest, TargetBackend.JVM_IR, testDataFilePath);
            }

            public void testAllFilesPresentInFinalMembersFromBuiltIns() throws Exception {
                KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/finalMembersFromBuiltIns"), Pattern.compile("^(.+)\\.kt$"), null, TargetBackend.JVM_IR, true);
            }

            @TestMetadata("enumMembers.kt")
            public void testEnumMembers() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/finalMembersFromBuiltIns/enumMembers.kt");
            }

            @TestMetadata("waitNotifyGetClass_ir.kt")
            public void testWaitNotifyGetClass_ir() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/finalMembersFromBuiltIns/waitNotifyGetClass_ir.kt");
            }
        }

        @TestMetadata("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/functionAndProperty")
        @TestDataPath("$PROJECT_ROOT")
        @RunWith(JUnit3RunnerWithInners.class)
        public static class FunctionAndProperty extends AbstractDiagnosticsTestWithJvmIrBackend {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest(this::doTest, TargetBackend.JVM_IR, testDataFilePath);
            }

            public void testAllFilesPresentInFunctionAndProperty() throws Exception {
                KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/functionAndProperty"), Pattern.compile("^(.+)\\.kt$"), null, TargetBackend.JVM_IR, true);
            }

            @TestMetadata("class.kt")
            public void testClass() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/functionAndProperty/class.kt");
            }

            @TestMetadata("classObject.kt")
            public void testClassObject() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/functionAndProperty/classObject.kt");
            }

            @TestMetadata("classPropertyInConstructor.kt")
            public void testClassPropertyInConstructor() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/functionAndProperty/classPropertyInConstructor.kt");
            }

            @TestMetadata("extensionFunctionAndNormalFunction.kt")
            public void testExtensionFunctionAndNormalFunction() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/functionAndProperty/extensionFunctionAndNormalFunction.kt");
            }

            @TestMetadata("extensionPropertyAndFunction.kt")
            public void testExtensionPropertyAndFunction() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/functionAndProperty/extensionPropertyAndFunction.kt");
            }

            @TestMetadata("functionAndSetter.kt")
            public void testFunctionAndSetter() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/functionAndProperty/functionAndSetter.kt");
            }

            @TestMetadata("functionAndVar.kt")
            public void testFunctionAndVar() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/functionAndProperty/functionAndVar.kt");
            }

            @TestMetadata("localClass.kt")
            public void testLocalClass() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/functionAndProperty/localClass.kt");
            }

            @TestMetadata("localClassInClass.kt")
            public void testLocalClassInClass() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/functionAndProperty/localClassInClass.kt");
            }

            @TestMetadata("nestedClass.kt")
            public void testNestedClass() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/functionAndProperty/nestedClass.kt");
            }

            @TestMetadata("object.kt")
            public void testObject() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/functionAndProperty/object.kt");
            }

            @TestMetadata("objectExpression.kt")
            public void testObjectExpression() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/functionAndProperty/objectExpression.kt");
            }

            @TestMetadata("objectExpressionInConstructor.kt")
            public void testObjectExpressionInConstructor() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/functionAndProperty/objectExpressionInConstructor.kt");
            }

            @TestMetadata("privateClassPropertyNoClash.kt")
            public void testPrivateClassPropertyNoClash() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/functionAndProperty/privateClassPropertyNoClash.kt");
            }

            @TestMetadata("topLevel.kt")
            public void testTopLevel() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/functionAndProperty/topLevel.kt");
            }

            @TestMetadata("topLevelDifferentFiles.kt")
            public void testTopLevelDifferentFiles() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/functionAndProperty/topLevelDifferentFiles.kt");
            }

            @TestMetadata("topLevelGetter.kt")
            public void testTopLevelGetter() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/functionAndProperty/topLevelGetter.kt");
            }

            @TestMetadata("trait_ir.kt")
            public void testTrait_ir() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/functionAndProperty/trait_ir.kt");
            }
        }

        @TestMetadata("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/specialNames")
        @TestDataPath("$PROJECT_ROOT")
        @RunWith(JUnit3RunnerWithInners.class)
        public static class SpecialNames extends AbstractDiagnosticsTestWithJvmIrBackend {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest(this::doTest, TargetBackend.JVM_IR, testDataFilePath);
            }

            public void testAllFilesPresentInSpecialNames() throws Exception {
                KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/specialNames"), Pattern.compile("^(.+)\\.kt$"), null, TargetBackend.JVM_IR, true);
            }

            @TestMetadata("classObjectCopiedField.kt")
            public void testClassObjectCopiedField() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/specialNames/classObjectCopiedField.kt");
            }

            @TestMetadata("classObjectCopiedFieldObject_ir.kt")
            public void testClassObjectCopiedFieldObject_ir() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/specialNames/classObjectCopiedFieldObject_ir.kt");
            }

            @TestMetadata("classObject_ir.kt")
            public void testClassObject_ir() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/specialNames/classObject_ir.kt");
            }

            @TestMetadata("dataClassCopy.kt")
            public void testDataClassCopy() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/specialNames/dataClassCopy.kt");
            }

            @TestMetadata("defaults.kt")
            public void testDefaults() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/specialNames/defaults.kt");
            }

            @TestMetadata("delegationBy_ir.kt")
            public void testDelegationBy_ir() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/specialNames/delegationBy_ir.kt");
            }

            @TestMetadata("enum.kt")
            public void testEnum() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/specialNames/enum.kt");
            }

            @TestMetadata("innerClassField_ir.kt")
            public void testInnerClassField_ir() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/specialNames/innerClassField_ir.kt");
            }

            @TestMetadata("instance_ir.kt")
            public void testInstance_ir() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/specialNames/instance_ir.kt");
            }

            @TestMetadata("propertyMetadataCache_ir.kt")
            public void testPropertyMetadataCache_ir() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/specialNames/propertyMetadataCache_ir.kt");
            }
        }

        @TestMetadata("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/statics")
        @TestDataPath("$PROJECT_ROOT")
        @RunWith(JUnit3RunnerWithInners.class)
        public static class Statics extends AbstractDiagnosticsTestWithJvmIrBackend {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest(this::doTest, TargetBackend.JVM_IR, testDataFilePath);
            }

            public void testAllFilesPresentInStatics() throws Exception {
                KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/statics"), Pattern.compile("^(.+)\\.kt$"), null, TargetBackend.JVM_IR, true);
            }

            @TestMetadata("jkjk.kt")
            public void testJkjk() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/statics/jkjk.kt");
            }

            @TestMetadata("kotlinClassExtendsJavaClass.kt")
            public void testKotlinClassExtendsJavaClass() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/statics/kotlinClassExtendsJavaClass.kt");
            }

            @TestMetadata("kotlinClassExtendsJavaClassExtendsJavaClass.kt")
            public void testKotlinClassExtendsJavaClassExtendsJavaClass() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/statics/kotlinClassExtendsJavaClassExtendsJavaClass.kt");
            }

            @TestMetadata("kotlinClassImplementsJavaInterface.kt")
            public void testKotlinClassImplementsJavaInterface() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/statics/kotlinClassImplementsJavaInterface.kt");
            }

            @TestMetadata("kotlinClassImplementsJavaInterfaceExtendsJavaInteface.kt")
            public void testKotlinClassImplementsJavaInterfaceExtendsJavaInteface() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/statics/kotlinClassImplementsJavaInterfaceExtendsJavaInteface.kt");
            }

            @TestMetadata("kotlinMembersVsJavaNonVisibleStatics.kt")
            public void testKotlinMembersVsJavaNonVisibleStatics() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/statics/kotlinMembersVsJavaNonVisibleStatics.kt");
            }
        }

        @TestMetadata("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/synthesized")
        @TestDataPath("$PROJECT_ROOT")
        @RunWith(JUnit3RunnerWithInners.class)
        public static class Synthesized extends AbstractDiagnosticsTestWithJvmIrBackend {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest(this::doTest, TargetBackend.JVM_IR, testDataFilePath);
            }

            public void testAllFilesPresentInSynthesized() throws Exception {
                KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/synthesized"), Pattern.compile("^(.+)\\.kt$"), null, TargetBackend.JVM_IR, true);
            }

            @TestMetadata("enumValuesValueOf.kt")
            public void testEnumValuesValueOf() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/synthesized/enumValuesValueOf.kt");
            }
        }

        @TestMetadata("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/traitImpl")
        @TestDataPath("$PROJECT_ROOT")
        @RunWith(JUnit3RunnerWithInners.class)
        public static class TraitImpl extends AbstractDiagnosticsTestWithJvmIrBackend {
            private void runTest(String testDataFilePath) throws Exception {
                KotlinTestUtils.runTest(this::doTest, TargetBackend.JVM_IR, testDataFilePath);
            }

            public void testAllFilesPresentInTraitImpl() throws Exception {
                KotlinTestUtils.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/traitImpl"), Pattern.compile("^(.+)\\.kt$"), null, TargetBackend.JVM_IR, true);
            }

            @TestMetadata("defaultVsNonDefault_ir.kt")
            public void testDefaultVsNonDefault_ir() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/traitImpl/defaultVsNonDefault_ir.kt");
            }

            @TestMetadata("oneTrait_ir.kt")
            public void testOneTrait_ir() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/traitImpl/oneTrait_ir.kt");
            }

            @TestMetadata("traitFunctionOverriddenByPropertyInTrait_ir.kt")
            public void testTraitFunctionOverriddenByPropertyInTrait_ir() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/traitImpl/traitFunctionOverriddenByPropertyInTrait_ir.kt");
            }

            @TestMetadata("traitPropertyOverriddenByFunctionInTrait_ir.kt")
            public void testTraitPropertyOverriddenByFunctionInTrait_ir() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/traitImpl/traitPropertyOverriddenByFunctionInTrait_ir.kt");
            }

            @TestMetadata("twoTraits_ir.kt")
            public void testTwoTraits_ir() throws Exception {
                runTest("compiler/testData/diagnostics/testsWithJvmBackend/duplicateJvmSignature/traitImpl/twoTraits_ir.kt");
            }
        }
    }
}
