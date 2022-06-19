package de.hsos.swe.abetterstart.common.boundary.web;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

/**
 * Allows proper error handling in the web layer.
 */
@CheckedTemplate
public class ErrorPage {

    public static native TemplateInstance error(int errorCode, String errorMessage);
}
