package de.hsos.swe.afairstartnew.common.boundary;

import org.eclipse.microprofile.openapi.OASFilter;
import org.eclipse.microprofile.openapi.models.OpenAPI;

import java.util.List;

/**
 * Hides the Web UI from the OpenAPI specification and therefore from the Swagger UI.
 */
public class OpenApiConfigurator implements OASFilter {

    @Override
    public void filterOpenAPI(OpenAPI openAPI) {
        // Hide all paths not starting with /api from the OpenAPI specification.
        List<String> paths = openAPI.getPaths().getPathItems().keySet().stream()
                .filter(path -> !path.startsWith("/api/"))
                .toList();
        paths.forEach(openAPI.getPaths()::removePathItem);

        // Hide all schemas of Qute templates (all non DTO schemas) from the OpenAPI specification.
        List<String> schemas = openAPI.getComponents().getSchemas().keySet().stream()
                .filter(schema -> !schema.endsWith("DTO"))
                .toList();
        schemas.forEach(openAPI.getComponents()::removeSchema);
    }
}
