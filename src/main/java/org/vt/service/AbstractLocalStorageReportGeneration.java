package org.vt.service;

import org.vt.model.Storage;

public abstract class AbstractLocalStorageReportGeneration implements ReportGeneration {

    @Override
    public Storage storage() {
        return Storage.LOCAL;
    }
}
