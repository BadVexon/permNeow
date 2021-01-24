package permNeow;

import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.interfaces.PostDungeonInitializeSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;


@SpireInitializer
public class permNeow implements PostDungeonInitializeSubscriber, PostInitializeSubscriber {

    public static final String MODNAME = "Neow Enabler";
    public static final String AUTHOR = "DarkVexon";
    public static final String DESCRIPTION = "Removes prerequisite of getting to the first boss to recieve Neow's blessing.";

    public static final float BUTTON_ENABLE_X = 350.0f;
    public static final float BUTTON_ENABLE_Y = 750.0f;

    public static Boolean AlwaysWhale = true;

    public permNeow() {
        BaseMod.subscribe(this);
    }

    public static void initialize() {
        new permNeow();
    }

    @Override
    public void receivePostDungeonInitialize() {

        if (AlwaysWhale) {
            // Do Neow's Blessing
            Settings.isTestingNeow = true;
        }

    }

    @Override
    public void receivePostInitialize() {
        // Mod badge
        Texture badgeTexture = new Texture("images/whale.png");

        ModPanel settingsPanel = new ModPanel();

        ModLabeledToggleButton enableAlwaysWhale = new ModLabeledToggleButton("Force Neow to give full blessing?",
                BUTTON_ENABLE_X, BUTTON_ENABLE_Y, Settings.CREAM_COLOR, FontHelper.charDescFont,
                AlwaysWhale, settingsPanel, (label) -> {
        }, (button) -> {
            SetBoolean(button.enabled);
        });

        settingsPanel.addUIElement(enableAlwaysWhale);

        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
    }

    public void SetBoolean(Boolean bool) {
        AlwaysWhale = bool;
    }

}