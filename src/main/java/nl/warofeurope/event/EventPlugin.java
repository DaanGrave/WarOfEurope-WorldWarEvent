package nl.warofeurope.event;

import co.aikar.commands.*;
import nl.warofeurope.event.commands.EventCommand;
import nl.warofeurope.event.commands.TellLocationCommand;
import nl.warofeurope.event.listeners.ChatListener;
import nl.warofeurope.event.listeners.DeathListener;
import nl.warofeurope.event.listeners.JoinListener;
import nl.warofeurope.event.models.Game;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.stream.Collectors;

public class EventPlugin extends JavaPlugin {
    private PaperCommandManager commandManager;
    public ScoreboardHandler scoreboardHandler;
    public Game game;

    @Override
    public void onEnable() {
        this.scoreboardHandler = new ScoreboardHandler();

        this.registerListeners(
                new JoinListener(this),
                new DeathListener(this),
                new ChatListener(this)
        );
        this.registerCommands(
                new EventCommand(this),
                new TellLocationCommand(this)
        );

        this.game = new Game(this);
    }

    private void registerListeners(Listener... listeners){
        Arrays.stream(listeners).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    private void registerCommands(BaseCommand... baseCommands){
        this.commandManager = new PaperCommandManager(this);

        this.commandManager.getCommandContexts().registerContext(Player.class, c -> {
            String name = c.popFirstArg();
            Player player = Bukkit.getPlayer(name);
            if (player != null){
                return player;
            } else {
                throw new InvalidCommandArgument(MessageKeys.COULD_NOT_FIND_PLAYER, "{search}", name);
            }
        });
        this.commandManager.getCommandContexts().registerContext(Material.class, c -> {
            String name = c.popFirstArg();
            try {
                return Material.valueOf(name);
            } catch (Exception e){
                throw new InvalidCommandArgument(MessageKeys.PLEASE_SPECIFY_ONE_OF, "{search}", ACFUtil.join(Arrays.stream(Material.values()).map(Material::toString).collect(Collectors.toSet())));
            }
        });

        Arrays.stream(baseCommands).forEach(baseCommand -> {
            this.commandManager.registerCommand(baseCommand);
        });
    }

    public static EventPlugin getInstance(){
        return EventPlugin.getPlugin(EventPlugin.class);
    }

    /**
     * Als je joint kom je in je juiste land.
     * Je wordt naar je land hok getpt.
     * 1m vooraf komt er een countdown in de chat.
     * bij start gaan alle iron bars weg en ontvangt iedereen kit 'event'
     * in scoreboard zie je hoeveel mensen van welk land er nog zijn.
     * mooie deathmessage
     * /tl kunnen in eigen land chat
     * prefix bij iedereen zijn hoofd + op tab + voor de naam.
     * bij dood gaan in gm 3 komen. Mensen die te laat joinen of unk zijn komen ook gm 3.
     * Als er nog 1 land over is wint dat land. Staat dan in de chat en als title, iedereen kan dan public GG zeggen.
     * /newgame - alle landen worden naar hun land spawn gezet en gecleared + scoreboard refresh. bij /start begint alles weer opnieuw.
     */
}
