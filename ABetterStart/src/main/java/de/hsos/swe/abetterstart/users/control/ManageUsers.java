package de.hsos.swe.abetterstart.users.control;

import de.hsos.swe.abetterstart.common.control.ManageEntity;
import de.hsos.swe.abetterstart.users.entity.UserExportDTO;
import de.hsos.swe.abetterstart.users.entity.UserImportDTO;

public interface ManageUsers extends ManageEntity<String, UserImportDTO, UserExportDTO> {

}
