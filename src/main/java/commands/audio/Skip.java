package commands.audio;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.Permission;
import utils.AudioController;

public class Skip extends Command {

    public Skip() {
        this.name = "skip";
        this.help = "Skips the currently playing track.";
        this.guildOnly = true;
        this.userPermissions = new Permission[] {Permission.MANAGE_CHANNEL};
        this.botPermissions = new Permission[] {Permission.VOICE_CONNECT, Permission.VOICE_SPEAK};
    }

    @Override
    protected void execute(CommandEvent event) {
        AudioController.getInstance().skipTrack(event.getTextChannel());
    }
}
