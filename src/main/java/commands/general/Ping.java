package commands.general;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;

import java.time.temporal.ChronoUnit;


public class Ping extends Command {
    public Ping() {
        this.name = "ping";
        this.help = "Responds with the current latency.";
        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent event) {
        event.reply("Pong: ...", m -> {
                long latency = event.getMessage().getCreationTime().until(m.getCreationTime(), ChronoUnit.MILLIS);
                m.editMessage("Pong: " + latency + "ms. Websocket: " + event.getJDA().getPing() + "ms.")
                        .queue();
         });
    }

}
