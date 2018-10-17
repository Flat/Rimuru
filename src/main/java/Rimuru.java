import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import commands.general.About;
import commands.general.Avatar;
import commands.general.EightBall;
import commands.general.Ping;
import net.dv8tion.jda.bot.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;


public class Rimuru {
    private final static Logger logger = LoggerFactory.getLogger(Rimuru.class);

    public static void main(String[] args) throws IllegalArgumentException, LoginException {
        String token = System.getenv("BOT_TOKEN");
        String owner = System.getenv("RIMURU_OWNER");

        EventWaiter waiter = new EventWaiter();
        CommandClientBuilder client = new CommandClientBuilder();
        client.setOwnerId(owner);
        client.setEmojis("\u2705", "\u26A0", "\u274C");
        client.setPrefix(null);
        client.setAlternativePrefix(".");
        client.setGame(Game.playing("Slime Quest"));
        client.addCommands(
                new About(),
                new Ping(),
                new Avatar(),
                new EightBall()
        );


        new DefaultShardManagerBuilder()
                .setToken(token)
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .setGame(Game.playing("loading..."))
                .addEventListeners(waiter, client.build())
                .build();
    }
}
