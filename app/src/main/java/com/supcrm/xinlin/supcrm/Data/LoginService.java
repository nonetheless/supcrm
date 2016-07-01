package com.supcrm.xinlin.supcrm.Data;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.supcrm.xinlin.supcrm.Dao.Chance;
import com.supcrm.xinlin.supcrm.Dao.ChanceProcess;
import com.supcrm.xinlin.supcrm.Dao.Commodity;
import com.supcrm.xinlin.supcrm.Dao.CommodityList;
import com.supcrm.xinlin.supcrm.Dao.Contract;
import com.supcrm.xinlin.supcrm.Dao.CustProcess;
import com.supcrm.xinlin.supcrm.Dao.Customer;
import com.supcrm.xinlin.supcrm.Dao.CustomerList;
import com.supcrm.xinlin.supcrm.Dao.LinkMan;
import com.supcrm.xinlin.supcrm.Dao.LinkManList;
import com.supcrm.xinlin.supcrm.Dao.UserList;
import com.supcrm.xinlin.supcrm.Dao.Users;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LoginService extends Service {

    /**
     * 进度条的最大值
     */
    public static final int MAX_PROGRESS = 100;
    /**
     * 进度条的进度值
     */
    private int progress = 0;


    /**
     * 更新进度的回调接口
     */

    public interface OnProgressListener{
        void onProgress(int i);
    }
    private OnProgressListener onProgressListener;

    /**
     * 注册回调接口的方法，供外部调用
     * @param onProgressListener
     */
    public void setOnProgressListener(OnProgressListener onProgressListener) {
        this.onProgressListener = onProgressListener;
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new MsgBinder();
    }



    public void login(final String id){
        final String parm="currentpage=-1&staffid="+id.trim();
        final String address="http://nqiwx.mooctest.net:8090/wexin.php/Api/Index/common_staff_json";
        System.out.println(id);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String arg=HttpUtil.startDownload(address,parm);
                System.out.println(arg);
                JsonObject obj=new JsonParser().parse(arg).getAsJsonObject();
                JsonObject temp;
                int i=0;
                while((temp=obj.getAsJsonObject(""+i))!=null){
                    System.out.println("gggg"+temp.toString());
                    Users a=new Users();
                    a.setName(getInt(temp.get("name").toString()));
                    a.setUserid(getInt(temp.get("userid").toString()));
                    a.setMoblie(getInt(temp.get("mobile").toString()));
                    a.setTel(getInt(temp.get("tel").toString()));
                    a.setEmail(getInt(temp.get("email").toString()));
                    a.setStaffid(Integer.parseInt(id));
                    UserList.getSingle(a);
                    progress=1;
                    break;
                }
                if(progress==0){
                    Users a=new Users();
                    a.setName("林鑫");
                    a.setStaffid(Integer.parseInt(id));
                    UserList.getSingle(a);
                    progress=1;
                }
                addLinkMan(id);
                addCommodity(id);

                if(onProgressListener != null){
                    onProgressListener.onProgress(progress);
                    System.out.print("gef");
                }
            }
        }).start();

    }

    public void newCust(final String id){
        final String parm="currentpage=-1&staffid="+id.trim();
        final String address="http://nqiwx.mooctest.net:8090/wexin.php/Api/Index/common_staff_json";
        System.out.println(id);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String arg=HttpUtil.startDownload(address,parm);
                newCustomer(arg,id,UserList.get().getUser().getName());
                addLinkMan(id);
                addCommodity(id);

                if(onProgressListener != null){
                    onProgressListener.onProgress(progress);
                    System.out.print("gef");
                }
            }
        }).start();

    }

    public void getOneCustomer(final int id) {
        progress=0;
        final int link=UserList.get().getUser().getStaffid();
        System.out.println(id);
        new Thread(new Runnable() {
            @Override
            public void run() {
                getProcess(id,link);
                getContract(id,link);
                getChance(id,link);
                progress=2;
                if(onProgressListener != null){
                    onProgressListener.onProgress(progress);
                    System.out.print("gef");
                }
            }
        }).start();
    }

    private void getContract(int id, int link) {
        ArrayList<Contract> c=new ArrayList<Contract>();
        for(int i=0;i<3;i++){
            Contract t=new Contract();
            int contractId=id*100+i;
            String name="合同"+contractId;
            int commodityId=i;
            int num=10*i;
            int customerId=id;
            String saler="员工"+link;
            Customer cu=CustomerList.get(null).getOneCustomer(id);
            String customerName=cu.getName();
            Date date=new Date();
            c.add(t);
        }

    }

    private void getProcess(int id, int link) {
        final String parm="currentpage=-1&sourcetype=1&staffid="+link+"&sourceid="+id;
        final String address="http://nqiwx.mooctest.net:8090/wexin.php/Api/Index/common_contacts_json";
        String arg=HttpUtil.startDownload(address,parm);
        System.out.println(arg);
        JsonObject obj=new JsonParser().parse(arg).getAsJsonObject();
        JsonObject temp;
        int i=0;
        ArrayList<CustProcess> list=new ArrayList<CustProcess>();
        int max=Integer.parseInt(obj.get("recordcount").toString());
        while((temp=obj.getAsJsonObject(""+i))!=null){
            CustProcess t1=new CustProcess();
            int pid=Integer.parseInt(getInt(temp.get("followupid").toString()));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
            String datestr=getInt(temp.get("followupid").toString()).split(" ")[0];
            Date date;

            try {
                System.out.println(datestr+"ggge");
                date=sdf.parse(datestr);
            } catch (ParseException e) {
                date=new Date();
            }

            String content=getInt(temp.get("content").toString());
            String stage=getInt(temp.get("followupremarks").toString());
            t1.setDate(date);
            t1.setId(id);
            t1.setContent(content);
            t1.setStage(stage);
            list.add(t1);
            i++;
        }


    }

    private void getChance(int id, int link) {
        final String parm="currentpage=-1&staffid="+link+"&customerid="+id;
        final String address="http://nqiwx.mooctest.net:8090/wexin.php/Api/Index/common_opportunity_json";
        String arg=HttpUtil.startDownload(address,parm);
        System.out.println(arg);
        JsonObject obj=new JsonParser().parse(arg).getAsJsonObject();
        JsonObject temp;
        int i=0;
        ArrayList<Chance> list=new ArrayList<Chance>();
        int max=Integer.parseInt(obj.get("recordcount").toString());
        while((temp=obj.getAsJsonObject(""+i))!=null){
            Chance a=new Chance();
            int cid=Integer.parseInt(getInt(temp.get("opportunityid").toString()));
            String name=getInt(temp.get("opportunitytitle").toString());
            int custId=id;
            int commodityId=i;
            String info=getInt(temp.get("businesstype").toString());
            MockData ma=new MockData();
            ArrayList<ChanceProcess> clist=ma.getChanceProcess();
            String saler="员工"+link;
            a.setCustId(custId);
            a.setId(cid);
            a.setList(clist);
            a.setName(name);
            a.setSaler(saler);
            a.setInfo(info);
            a.setCommodityId(commodityId);
            list.add(a);
            i++;
        }

    }

    public int addLinkMan(final String id){
        final String parm="currentpage=-1&staffid="+id.trim();
        final String address="http://nqiwx.mooctest.net:8090/wexin.php/Api/Index/common_contacts_json";
        String arg=HttpUtil.startDownload(address,parm);
        System.out.println(arg);
        JsonObject obj=new JsonParser().parse(arg).getAsJsonObject();
        JsonObject temp;
        int i=0;
        ArrayList<LinkMan> list=new ArrayList<LinkMan>();
        int max=Integer.parseInt(obj.get("recordcount").toString());
        while((temp=obj.getAsJsonObject(""+i))!=null){
            LinkMan link=new LinkMan();
            System.out.println(temp.get("contactsid").toString());
            int linkId=Integer.parseInt(getInt(temp.get("contactsid").toString()));
            String name=getInt(temp.get("contactsname").toString());
            String tel=getInt(temp.get("contactsmobile").toString());
            String qq=getInt(temp.get("contactsqq").toString());
            String company=getInt(temp.get("contactsaddress").toString());
            int custId=Integer.parseInt(getInt(temp.get("customerid").toString()));
            link.setCompany(company);
            link.setTel(tel);
            link.setQQ(qq);
            link.setName(name);
            //link.setCustomerId(custId);
            link.setId(linkId);
            list.add(link);
            i++;
            if(i==max+1){
                break;
            }
        }
        LinkManList.get(null).changeList(list);
        return 1;

    }

    private String getInt(String id) {
        if (id.contains("null")){
            return "0";
        }
        String b=id.substring(1,id.length()-1);
        return b;
    }

    private void addCommodity(final String id){
        final String parm="currentpage=-1&staffid="+id.trim();
        final String address="http://nqiwx.mooctest.net:8090/wexin.php/Api/Index/common_product_json";
        String arg=HttpUtil.startDownload(address,parm);
        System.out.println(arg);
        JsonObject obj=new JsonParser().parse(arg).getAsJsonObject();
        JsonObject temp;
        int i=0;
        ArrayList<Commodity> list=new ArrayList<Commodity>();
        while((temp=obj.getAsJsonObject(""+i))!=null) {
            Commodity com=new Commodity();
            String name=getInt(temp.get("productname").toString());
            int linkid =Integer.parseInt(getInt(temp.get("productid").toString()));
            double price=Double.parseDouble(getInt(temp.get("standardprice").toString()));
            String info=getInt(temp.get("introduction").toString());
            String kind=getInt(temp.get("classification").toString());
            int num=(int)Double.parseDouble(getInt(temp.get("unitcost").toString()));
            com.setId(linkid);
            com.setName(name);
            com.setPrice(price);
            com.setInfo(info);
            com.setKind(kind);
            com.setNum(num);
            addCustomer("2");
            list.add(com);
            i++;
        }
        CommodityList.get(null).changelist(list);
    }

    private void addCustomer(final String id){
        final String parm="currentpage=-1&staffid="+id.trim();
        final String address="http://nqiwx.mooctest.net:8090/wexin.php/Api/Index/common_customer_json";
        String arg=HttpUtil.startDownload(address,parm);
        System.out.println(arg);
        JsonObject obj=new JsonParser().parse(arg).getAsJsonObject();
        JsonObject temp;
        int i=0;
        ArrayList<Customer> list=new ArrayList<Customer>();
        while((temp=obj.getAsJsonObject(""+i))!=null) {
            Customer cust=new Customer();
            String name=getInt(temp.get("customername").toString());
            int custid=Integer.parseInt(getInt(temp.get("customerid").toString()));
            ArrayList<Contract> contract=new ArrayList<Contract>();
            ArrayList<Chance> chance=new ArrayList<Chance>();
            ArrayList<CustProcess> processes=new ArrayList<CustProcess>();
            int linkid=1;
            cust.setId(custid);
            cust.setLinkMan_id(linkid);
            cust.setContract(contract);
            cust.setProcesses(processes);
            cust.setChance(chance);
            list.add(cust);
            newCustomer("","","");
            i++;
        }

    }

    private void newCustomer(String name,String link,String custname ){
        final String parm="currentpage=-1&staffid=";
        final String address="http://nqiwx.mooctest.net:8090/wexin.php/Api/Index/common_customer_json";
        String arg=HttpUtil.startDownload(address,parm);
        System.out.println(arg);
        JsonObject obj=new JsonParser().parse(arg).getAsJsonObject();
        JsonObject temp;
        int i=0;
        ArrayList<Customer> list=new ArrayList<Customer>();
        while((temp=obj.getAsJsonObject(""+i))!=null) {
            Customer cust=new Customer();
            String name1=getInt(temp.get("customername").toString());
            int custid=Integer.parseInt(getInt(temp.get("customerid").toString()));
            ArrayList<Contract> contract=new ArrayList<Contract>();
            ArrayList<Chance> chance=new ArrayList<Chance>();
            ArrayList<CustProcess> processes=new ArrayList<CustProcess>();
            int linkid=1;
            cust.setId(custid);
            cust.setLinkMan_id(linkid);
            cust.setContract(contract);
            cust.setProcesses(processes);
            cust.setChance(chance);
            list.add(cust);
            i++;
        }
    }

    public class MsgBinder extends Binder{
        public LoginService getService(){
            return LoginService.this;
        }
    }
}
