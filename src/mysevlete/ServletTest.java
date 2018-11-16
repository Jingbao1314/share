package mysevlete;


import com.google.gson.Gson;
import control.*;
import pojo.Goods;
import pojo.Trade;
import pojo.User;
import util.redisutil.RedisOperating;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by jingbao on 18-4-22.
 */

public class ServletTest {

    public static State doServlet(Message message){
        String url=message.getUrl();
        String gson=message.getData();
        String response="{\"state\":0}";
        State status=new State();
        String []result=url.split("/");
        Class cls= null;
        Gson g=new Gson();
        Constructor con=null;
//        int state;
//        State s=new State();
        try {
//            cls = Class.forName("control."+result[1]);
//            Constructor con=cls.getConstructor();
            System.out.println(result[1]+"----"+result[2]);
            switch (result[1]){
                case "Store":
                    cls = Class.forName("control."+result[1]);
                    con=cls.getConstructor();
                    Store store= (Store) con.newInstance();
                    Method doStore=cls.getMethod(result[2], Goods.class);
                    status= (State) doStore.invoke(store,new
                            Gson().fromJson(gson,Goods.class));
                    response=g.toJson(status);
//                    if(result[2].equals("doStore")){
//
//                    System.out.println("");
//
//                    }else if(result[2].equals("doChange")){
//                        cls = Class.forName("control."+result[1]);
//                        con=cls.getConstructor();
//                        Store store= (Store) con.newInstance();
//                        Method doChange=cls.getMethod(result[2],Goods.class);
//                        status= (State) doChange.invoke(store,new
//                                Gson().fromJson(gson,Goods.class));
//                        response=g.toJson(status);
////                    System.out.println(doStore.invoke(store,object));
//                    }
                    return status;
                case "Pay":

                    cls = Class.forName("control."+result[1]);
                    con=cls.getConstructor();
                    Pay pay= (Pay) con.newInstance();
                    Method method=cls.getMethod(result[2],Goods.class);
                    status= (State) method.invoke(pay,new
                                Gson().fromJson(gson,Goods.class));
//                    status.setState(0);
                    return status;
                case "Login":
//                    if (message.getCookies()!=null){
//                        User use=new Gson().fromJson(message.getCookies(),
//                                User.class);
//                        RedisOperating r=new RedisOperating();
//                        if (r.exists(use.getPhone())){
//                            status.setState(1);//成功
//                        }else {
//                            status.setState(0);//失败
//
//                        }
//
//                    }else {
//
//                    }
                    cls = Class.forName("control."+result[1]);
                    con=cls.getConstructor();
                    Login login= (Login) con.newInstance();
                    Method doLogin=cls.getMethod(result[2], User.class);
                    int s= (int) doLogin.invoke(login,new
                            Gson().fromJson(gson,User.class));
                    status.setState(s);
                    return status;

                case "Register":
                    RedisOperating o=new RedisOperating();
                    User user=new Gson().fromJson(gson,User.class);
                    if(o.exists(user.getPhone())){
                        status.setState(2);
                    }else {
                        cls = Class.forName("control."+result[1]);
                        con=cls.getConstructor();
                        Register register= (Register) con.newInstance();
                        Method doReister=cls.getMethod(result[2], User.class);
                        System.out.println("Register-----------");
                        status= (State) doReister.invoke(register,user);
                        System.out.println("Register-----------");
                    }
//                    status.setServer(result[2]);

//                    status.setCookies(MyCookies.getCookies(user.getPhone(),
//                            user.getPassword()));
//                    s=new State();
//                    s.setState(state);
//                    response=g.toJson(s);
//                    System.out.println(doStore.invoke(store,object));
                    System.out.println("Register-----------");
                    return status;
                case "Improve":
                    cls = Class.forName("control."+result[1]);
                    con=cls.getConstructor();
                    Improve improve= (Improve) con.newInstance();
                    Method doimprove=cls.getMethod(result[2], User.class);
//                    User Iuser=new Gson().fromJson(gson,User.class);
                    System.out.println("Register-----------");
                    status= (State) doimprove.invoke(improve,new Gson().fromJson
                            (gson,User.class));
                    System.out.println("IMPROVE-----------");
//                    s=new State();
//                    s.setState(state);
//                    response=g.toJson(s);
//                    System.out.println(doStore.invoke(store,object));
                    return status;
                case "Recharge":

                    cls = Class.forName("control."+result[1]);
                    con=cls.getConstructor();
                    Recharge recharge= (Recharge) con.newInstance();
                    Method doRecharge=cls.getMethod(result[2], User.class);
//                    User Ruser=new Gson().fromJson(gson,User.class);
                    status= (State) doRecharge.invoke(recharge,new Gson().fromJson
                            (gson,User.class));
//                    s=new State();
//                    s.setState(state);
//                    response=g.toJson(s);
//                    System.out.println(doStore.invoke(store,object));
                    return status;
                case "Trades":

                    cls = Class.forName("control."+result[1]);
                    con=cls.getConstructor();
                    Trades trades= (Trades) con.newInstance();
                    Method doTrade=cls.getMethod(result[2], Trade.class);
//                    User Ruser=new Gson().fromJson(gson,User.class);
                    status= (State) doTrade.invoke(trades,new Gson().fromJson
                            (gson,Trade.class));
//                    s=new State();
//                    s.setState(state);
//                    response=g.toJson(s);
//                    System.out.println(doStore.invoke(store,object));
                    return status;
                case "Open":
//                    gson="{id:"+gson+"}";
                    cls = Class.forName("control."+result[1]);
                    con=cls.getConstructor();
                    Open open= (Open) con.newInstance();
                    Method doOpen=cls.getMethod(result[2],Goods.class);
                    status= (State) doOpen.invoke(open,new
                            Gson().fromJson(gson,Goods.class));
//                    status.setState(0);
                    return status;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return status;
    }
}
