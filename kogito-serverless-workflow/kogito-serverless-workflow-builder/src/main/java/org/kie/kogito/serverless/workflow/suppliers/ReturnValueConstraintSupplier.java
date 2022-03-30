package org.kie.kogito.serverless.workflow.suppliers;

import org.jbpm.process.instance.impl.ConstraintEvaluator;
import org.jbpm.process.instance.impl.ReturnValueConstraintEvaluator;

import java.util.function.Supplier;

public class ReturnValueConstraintSupplier extends ReturnValueConstraintEvaluator implements Supplier<ConstraintEvaluator> {

    private final ReturnValueConstraintEvaluator constraintEvaluator;

    public ReturnValueConstraintSupplier(String dialect, String name, String type, String constraint, boolean isDefault){
        constraintEvaluator = new ReturnValueConstraintEvaluator();
        constraintEvaluator.setDialect(dialect);
        constraintEvaluator.setName(name);
        constraintEvaluator.setDefault(isDefault);
        constraintEvaluator.setType(type);
        constraintEvaluator.setConstraint(constraint);
    }

    @Override
    public ConstraintEvaluator get() {
        return constraintEvaluator;
    }
}
