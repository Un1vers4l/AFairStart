package de.hsos.swe.afairstartnew.common.boundary.web;

import de.hsos.swe.afairstartnew.common.entity.ImportDTO;
import io.quarkus.qute.TemplateInstance;

/**
 * Each UI (Qute) resource that accesses a list of entities should extend this class.
 * In this project some HTTP methods are only used here and ignored in the implementations,
 * but normally all HTTP methods should be used even if they only return the default implementation.
 * NOTE: This is completely optional and a preference from Prof Roosmann.
 */
public interface ListPage<IDto extends ImportDTO<?>> {

    default TemplateInstance get() {
        return null;
    }
    default TemplateInstance post(IDto iDto) {
        return null;
    }
}
