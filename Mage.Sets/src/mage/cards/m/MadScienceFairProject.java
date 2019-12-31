package mage.cards.m;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import mage.Mana;
import mage.abilities.Ability;
import mage.abilities.costs.common.TapSourceCost;
import mage.abilities.effects.common.ManaEffect;
import mage.abilities.mana.SimpleManaAbility;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.choices.ChoiceColor;
import mage.choices.ManaChoice;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.Zone;
import mage.counters.CounterType;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;

/**
 *
 * @author spjspj
 */
public final class MadScienceFairProject extends CardImpl {

    public MadScienceFairProject(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId, setInfo, new CardType[]{CardType.ARTIFACT}, "{3}");

        // {T}: Roll a six-sided die. On a 3 or lower, target player adds {C}. Otherwise, that player adds one mana of any color they choose.
        this.addAbility(new SimpleManaAbility(Zone.BATTLEFIELD, new MadScienceFairManaEffect(), new TapSourceCost()));
    }

    public MadScienceFairProject(final MadScienceFairProject card) {
        super(card);
    }

    @Override
    public MadScienceFairProject copy() {
        return new MadScienceFairProject(this);
    }
}

class MadScienceFairManaEffect extends ManaEffect {

    public MadScienceFairManaEffect() {
        super();
        this.staticText = "Roll a six-sided die. On a 3 or lower, target player adds {C}. Otherwise, that player adds one mana of any color they choose";
    }

    public MadScienceFairManaEffect(final MadScienceFairManaEffect effect) {
        super(effect);
    }

    @Override
    public MadScienceFairManaEffect copy() {
        return new MadScienceFairManaEffect(this);
    }

    @Override
    public Mana produceMana(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller != null) {
            int amount = controller.rollDice(game, 6);
            if (amount <= 3) {
                return Mana.ColorlessMana(1);
            } else {
                return ManaChoice.chooseAnyColor(controller, game, 1);
            }
        }
        return null;
    }

    @Override
    public List<Mana> getNetMana(Game game, Ability source) {
        return null;
    }

}
