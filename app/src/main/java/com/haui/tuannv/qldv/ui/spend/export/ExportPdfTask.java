package com.haui.tuannv.qldv.ui.spend.export;

import android.os.AsyncTask;
import android.os.Environment;
import com.haui.tuannv.qldv.data.local.model.Payment;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by tuanbg on 3/15/17.
 */
public class ExportPdfTask extends AsyncTask<Void, String, String> {
    private static final int NUMBER_COLUMN_TABLE = 4;
    private static final String FOLDER_NAME = "export_file_qldv";
    private static final String FILE_NAME_SAVED_PDF = "_khoanchi.pdf";
    private static final String TITLE_ = "Nội dung chi";
    private static final String TITLE_AMOUNT = "Số tiền chi";
    private static final String TITLE_DATE = "Ngày chi";
    private static final String TITLE_USER = "Người chi";
    private List<Payment> mList;
    private CallBackExport mCallBack;

    public ExportPdfTask(List<Payment> list, CallBackExport callBackExport) {
        mList = list;
        mCallBack = callBackExport;
    }

    @Override
    protected String doInBackground(Void... params) {
        return createPdf();
    }

    private String createPdf() {
        File exportDir = new File(Environment.getExternalStorageDirectory(), FOLDER_NAME);
        if (!exportDir.exists()) exportDir.mkdirs();
        File file = new File(exportDir, getCurentTime() + FILE_NAME_SAVED_PDF);
        OutputStream output;
        try {
            output = new FileOutputStream(file);
            Document document = new Document();
            PdfWriter.getInstance(document, output);
            document.open();
            document.add(generatePDFTable());
            document.close();
            return file.getPath();
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
            return "";
        }
    }

    private PdfPTable generatePDFTable() {
        // 2 columns table
        PdfPTable table = new PdfPTable(NUMBER_COLUMN_TABLE);
        table.addCell(TITLE_);
        table.addCell(TITLE_AMOUNT);
        table.addCell(TITLE_DATE);
        table.addCell(TITLE_USER);
        for (int i = 0; i < mList.size(); i++) {
            table.addCell(mList.get(i).getTitle());
            table.addCell(String.valueOf(mList.get(i).getAmount()));
            table.addCell(mList.get(i).getPaidDate());
            table.addCell(String.valueOf(mList.get(i).getUserName()));
        }
        return table;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (!s.isEmpty()) {
            mCallBack.onExportSuccess(s);
        } else {
            mCallBack.onExportError();
        }
    }

    public static String getCurentTime() {
        Calendar cal = Calendar.getInstance();
        Date currentLocalTime = cal.getTime();
        DateFormat date = new SimpleDateFormat("HHmmss", Locale.getDefault());
        return date.format(currentLocalTime);
    }

    public interface CallBackExport {
        void onExportError();

        void onExportSuccess(String path);
    }
}
