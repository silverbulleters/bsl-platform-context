package org.silverbulleters.bsl.platform.context.internal;

import org.silverbulleters.bsl.platform.context.platform.ContextType;
import org.silverbulleters.bsl.platform.context.platform.Signature;
import org.silverbulleters.bsl.platform.context.types.ObjectDescription;
import org.silverbulleters.bsl.platform.context.types.ObjectName;

import java.util.List;

public abstract class GeneralMethodBuilder<T extends BaseMethod> implements MethodBuilder{

    protected T method;

    @Override
    public void setName(ObjectName name) {
        method.setName(name);
    }

    @Override
    public void setDescription(ObjectDescription description) {
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
