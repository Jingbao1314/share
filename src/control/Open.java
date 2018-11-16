package control;

import com.google.gson.Gson;
import pojo.Goods;
import util.JdbcMethod.StoreJdbc;

/**
 * Created by jingbao on 18-10-26.
 */
public class Open {
    public State doOpen(Goods goods){
        State state=new State();
        String res=StoreJdbc.get(goods);
        if(res.equals("{}")){
            state.setState(0);
        }else {
            state.setState(1);
        }
        return state;
    }
}
