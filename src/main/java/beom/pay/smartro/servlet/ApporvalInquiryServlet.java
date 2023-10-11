package beom.pay.smartro.servlet;

import javax.servlet.annotation.WebServlet;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.HashMap;

@Slf4j
@WebServlet(name ="ApprovalInquiry",urlPatterns ="/approval-inquiry")
public class ApporvalInquiryServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = "https://tapproval.smartropay.co.kr/payment/approval/approvalInquiry.do";		// 테스트
//String url = "https://approval.smartropay.co.kr/payment/approval/approvalInquiry.do";   	// 운영

        String Tid       = request.getParameter("Tid")==null?"":request.getParameter("Tid");
        String TrAuthKey = request.getParameter("TrAuthKey")==null?"":request.getParameter("TrAuthKey");
        log.info(Tid + TrAuthKey);

        HashMap<String, Object> result = callApi(TrAuthKey , Tid, url);
        log.info(result.toString());

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write("<html>");
        printWriter.write("<body>");
        printWriter.write(result.toString());
        printWriter.write("</body>");
        printWriter.write("</html>");

    }
    public HashMap<String, Object> callApi(String TrAuthKey, String Tid, String callUrl) {

        StringBuilder responseBody = null;
        HashMap<String, Object> result = new HashMap<>();

        // http urlCall 승인 요청 및 TrAuthKey 유효성 검증
        int connectTimeout = 1000;
        int readTimeout = 10000; // 가맹점에 맞게 TimeOut 조절

        URL url = null;
        HttpsURLConnection connection = null;

        try {
            SSLContext sslCtx = SSLContext.getInstance("TLSv1.2");
            sslCtx.init(null, null, new SecureRandom());

            url = new URL(callUrl);
            System.out.println(" url " + url.toString());
            connection = (HttpsURLConnection)url.openConnection();
            connection.setSSLSocketFactory(sslCtx.getSocketFactory());

            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setConnectTimeout(connectTimeout);
            connection.setReadTimeout(readTimeout);

            OutputStreamWriter osw = new OutputStreamWriter(new BufferedOutputStream(connection.getOutputStream()) , "utf-8" );

            JSONObject body = new JSONObject();
            body.put("Tid" ,Tid);
            body.put("TrAuthKey" ,TrAuthKey);

            char[] bytes = body.toString().toCharArray();
            osw.write(bytes,0,bytes.length);
            osw.flush();
            osw.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = null;
            responseBody =  new StringBuilder();
            while ((line = br.readLine()) != null) {
                System.out.println(" response " +  line);
                responseBody.append(line);
            }
            br.close();

            // 결제결과
            result = new ObjectMapper().readValue(responseBody.toString(), HashMap.class);

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
