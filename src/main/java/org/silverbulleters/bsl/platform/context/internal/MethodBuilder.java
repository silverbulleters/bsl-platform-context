package org.silverbulleters.bsl.platform.context.internal;

import org.silverbulleters.bsl.platform.context.platform.ContextType;
import org.silverbulleters.bsl.platform.context.platform.Signature;
import org.silverbulleters.bsl.platform.context.types.Resource;

import java.util.List;

public interface MethodBuilder {
    void setName(Resource name);
    void setDescription (Resource description);
    void setParent(ContextType parent);
    void setSignatures(List<Signature>signatures);
}
