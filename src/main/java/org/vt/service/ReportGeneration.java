package org.vt.service;

import org.vt.model.FileDto;
import org.vt.model.Storage;

public interface ReportGeneration {
    Storage storage();
    String buildDataReport(final FileDto fileDto);
}
