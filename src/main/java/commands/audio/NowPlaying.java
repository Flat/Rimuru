package commands.audio;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import utils.AudioController;

public class NowPlaying extends Command {

    public NowPlaying() {
        this.name = "nowplaying";
        this.help = "Shows the currently playing track information";
        this.botPermissions = new Permission[] {Permission.MESSAGE_WRITE, Permission.MESSAGE_EMBED_LINKS};
    }

    @Override
    protected void execute(CommandEvent event) {
        if (event.getGuild().getAudioManager().isConnected()) {
            AudioTrack audioTrack = AudioController.getInstance().getCurrentlyPlaying(event.getTextChannel());
            if (audioTrack != null){
                long duration = audioTrack.getDuration();
                long position = audioTrack.getPosition();
                String status = String.format("%s / %s", formatTimestamp(position), formatTimestamp(duration));
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setTitle("Current Track", audioTrack.getInfo().uri);
                embedBuilder.addField("Title", audioTrack.getInfo().title, false);
                embedBuilder.addField("Artist", audioTrack.getInfo().author, false);
                embedBuilder.addField("Progress", status, false);
                event.reply(embedBuilder.build());
            } else {
                event.reply("No track currently playing.");
            }

        }
    }
    private String formatTimestamp(long milliseconds) {
        int seconds = (int) (milliseconds / 1000) % 60;
        int minutes = (int) ((milliseconds / (1000*60)) % 60);
        int hours = (int) ((milliseconds / (1000*60*60)) % 24);
        if (hours > 0) {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format("%02d:%02d",  minutes, seconds);
        }

    }
}
