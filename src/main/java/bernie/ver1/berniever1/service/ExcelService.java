package bernie.ver1.berniever1.service;

import bernie.ver1.berniever1.utils.ExcelWriteUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelService {
    public byte[] exportDataToExcel(List<String> headers, List<Object[]> data) throws IOException {
        return ExcelWriteUtil.exportToExcel(headers, data);
    }
}
