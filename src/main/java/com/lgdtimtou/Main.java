package com.lgdtimtou;

import com.lgdtimtou.ChatEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.yaml.snakeyaml.Yaml;

import javax.security.auth.login.LoginException;
import java.io.InputStream;
import java.util.HashMap;

public class Main {

    public static JDABuilder jdaBuilder;
    public static JDA jda;

    public static void main(String[] args) throws LoginException {
        jdaBuilder = JDABuilder.createDefault((String) loadConfig().get("token"));
        jdaBuilder.setActivity(Activity.playing("met zen lul"));
        jdaBuilder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        jda = jdaBuilder.build();
        jda.addEventListener(new ChatEvent());
        NoSQL.connect();
        System.out.println(NoSQL.getRecord());
    }

    private static HashMap<String, Object> loadConfig(){
        Yaml yaml = new Yaml();
        InputStream is = Main.class.getClassLoader().getResourceAsStream("config.yml");;
        return yaml.load(is);
    }


}
