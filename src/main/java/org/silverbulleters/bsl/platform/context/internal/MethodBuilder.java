package org.silverbulleters.bsl.platform.context.internal;

import org.silverbulleters.bsl.platform.context.platform.ContextType;
import org.silverbulleters.bsl.platform.context.platform.Signature;
import org.silverbulleters.bsl.platform.context.types.ObjectDescription;
import org.silverbulleters.bsl.platform.context.types.ObjectName;

import java.util.List;

public interface MethodBuilder {
    void setName(ObjectName name);
    void setDescription (ObjectDescription description);
    void setParent(ContextType parent);
    void setSignatures(List<Signature>signatures);
}
