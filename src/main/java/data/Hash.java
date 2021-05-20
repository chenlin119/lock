package data;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
@Slf4j
public class Hash {
    public static void main(String[] args) {
        HashMap<String,String> map=new HashMap<>();
        map.put("name","chenlin");
        log.info(map.get("name"));
    }
}
