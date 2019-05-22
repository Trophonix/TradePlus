package com.trophonix.tradeplus.logging;

import com.trophonix.tradeplus.util.ItemFactory;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
public class TradeLog implements PostProcessor {

  private UUID player1, player2;
  private transient String lastKnownName1, lastKnownName2;
  private List<ItemFactory> player1Items, player2Items;
  private List<ExtraOffer> player1ExtraOffers, player2ExtraOffers;
  private Date time;

  public TradeLog() { }

  public TradeLog(OfflinePlayer player1, OfflinePlayer player2, List<ItemFactory> player1Items,
                  List<ItemFactory> player2Items, List<ExtraOffer> player1ExtraOffers,
                  List<ExtraOffer> player2ExtraOffers) {
    this.player1 = player1.getUniqueId();
    this.player2 = player2.getUniqueId();
    this.lastKnownName1 = player1.getName();
    this.lastKnownName2 = player2.getName();
    player1Items.sort((o1, o2) -> Integer.compare(o2.getAmount(), o1.getAmount()));
    this.player1Items = player1Items;
    player2Items.sort((o1, o2) -> Integer.compare(o2.getAmount(), o1.getAmount()));
    this.player2Items = player2Items;
    this.player1ExtraOffers = player1ExtraOffers;
    this.player2ExtraOffers = player2ExtraOffers;
    this.time = new Date();
  }

  @Override public void doPostProcessing() {
    OfflinePlayer op1 = Bukkit.getOfflinePlayer(player1);
    if (op1.getName() != null) lastKnownName1 = op1.getName();
    OfflinePlayer op2 = Bukkit.getOfflinePlayer(player2);
    if (op2.getName() != null) lastKnownName2 = op2.getName();
  }

  @Getter
  public static class ExtraOffer {

    private String id;
    private double value;

    public ExtraOffer() { }

    public ExtraOffer(String id, double value) {
      this.id = id;
      this.value = value;
    }

  }

}
