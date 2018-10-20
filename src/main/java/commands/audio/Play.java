package commands.audio;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import utils.AudioController;

public class Play extends Command {
    public Play() {
        this.name = "play";
        this.help = "Plays a URL in your current voice channel.";
        this.guildOnly = true;
        this.arguments = "[url]";
        this.botPermissions = new Permission[] {Permission.VOICE_CONNECT, Permission.VOICE_SPEAK};
    }

    @Override
    protected void execute(CommandEvent event) {
        Member member = event.getMessage().getMember();
        if (member.getVoiceState().getChannel() != null && event.getArgs() != null && !event.getArgs().isEmpty()) {
            AudioController.getInstance().loadAndPlay(event.getTextChannel(), event.getArgs(), member.getVoiceState().getChannel());
        }
    }
}
