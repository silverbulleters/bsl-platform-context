package org.silverbulleters.bsl.platform.context.internal;

import org.silverbulleters.bsl.platform.context.platform.ContextType;
import org.silverbulleters.bsl.platform.context.platform.Signature;
import org.silverbulleters.bsl.platform.context.types.Resource;

import java.util.List;

public abstract class GeneralMethodBuilder<T extends BaseMethod> implements MethodBuilder{

    protected T method;

    @Override
    public void setName(Resource name) {
        method.setName(name);
    }

    @Override
    public void setDescription(Resource description) {
        method.setDescription(description);
    }

    @Override
    public void setParent(ContextType parent) {
        method.setParent(parent);
    }

    @Override
    public void setSignatures(List<Signature> signatures) {
        method.setSignatures(signatures);
    }

    public T build() {
        return method;
    }
}
