package mfec.action;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import mfec.services.BaseBusiness;

/**
 * Servlet implementation class InitServlet
 */
@WebServlet("/InitServlet")
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public InitServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final String jsonExportPath = "C:\\\\MFEC_temp\\\\data.json";
		response.setContentType("text/x-json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		InputStream fileContent = null;
		List<FileItem> items = null;	
		
		try {

			items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
	        for (FileItem item : items) {
	            if (!item.isFormField()) {
	                // Process form file field (input type="file").
	                fileContent = item.getInputStream();

	            }
	        }
	    
	        String jsonDataStr = BaseBusiness.calculatePhonCharge(fileContent);
	        
	        try (FileWriter file = new FileWriter(jsonExportPath)) {
				file.write(jsonDataStr);
			}
	        
			out.print(jsonDataStr);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response.setStatus(500);
			out.print(e.getMessage());
			
		}finally {
			if(items != null) {
				items.clear();
				items = null;
			}
			if(fileContent!=null) {
				fileContent.close();
				fileContent=null;
			}
			out.flush();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
