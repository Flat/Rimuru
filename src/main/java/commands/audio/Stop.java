package commands.audio;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.Permission;
import utils.AudioController;

public class Stop extends Command {
    public Stop() {
        this.name = "stop";
        this.help = "Stops the current track and clears the queue.";
        this.guildOnly = true;
        this.userPermissions = new Permission[] {Permission.KICK_MEMBERS};
        this.botPermissions = new Permission[] {Permission.VOICE_CONNECT, Permission.VOICE_SPEAK};
    }

    @Override
    protected void execute(CommandEvent event) {
        AudioController.getInstance().stop(event.getTextChannel());
    }
}
