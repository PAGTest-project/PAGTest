
package org.openapitools.openapidiff.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.openapitools.openapidiff.core.model.ChangedOpenApi;

public class OpenApiCompare_fromFilesTest {

    @Test
    public void testFromFilesWithValidFiles() {
        File oldFile = new File("src/test/resources/petstore_v2_1.yaml");
        File newFile = new File("src/test/resources/petstore_v2_2.yaml");

        ChangedOpenApi changedOpenApi = OpenApiCompare.fromFiles(oldFile, newFile, Collections.emptyList());

        assertThat(changedOpenApi).isNotNull();
        assertThat(changedOpenApi.getNewEndpoints()).isNotEmpty();
        assertThat(changedOpenApi.getMissingEndpoints()).isNotEmpty();
    }

    @Test
    public void testFromFilesWithInvalidFiles() {
        File oldFile = new File("non_existent_file_old.yaml");
        File newFile = new File("non_existent_file_new.yaml");

        assertThrows(RuntimeException.class, () -> {
            OpenApiCompare.fromFiles(oldFile, newFile, Collections.emptyList());
        });
    }
}
