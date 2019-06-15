package com.trophonix.tradeplus.logging;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.UnaryOperator;

public class Logs implements List<TradeLog> {

  private static final DateFormat folderNameFormat = new SimpleDateFormat(
      "'session_'yyyy-MM-dd'_'HH:mm:ss");

  private static final DateFormat fileNameFormat = new SimpleDateFormat(
      "'{player1}-{player2}_'HH:mm:ss'.json'");

  private File folder;
  private List<TradeLog> logs = new ArrayList<>();

  private Gson gson = new GsonBuilder()
      .registerTypeAdapter(UUID.class, new TypeAdapter<UUID>() {
        @Override public void write(JsonWriter jsonWriter, UUID uuid) throws IOException {
          jsonWriter.value(uuid.toString());
        }

        @Override public UUID read(JsonReader jsonReader) throws IOException {
          return UUID.fromString(jsonReader.nextString());
        }
      }).registerTypeAdapterFactory(new PostProcessingEnabler())
        .registerTypeHierarchyAdapter(List.class, new NullEmptyListAdapter())
        .registerTypeHierarchyAdapter(Number.class, new NullZeroNumberAdapter())
        .setPrettyPrinting()
        .create();

  public Logs(File parent, String file) throws IOException {
    if (!parent.exists()) {
      parent.mkdirs();
    }
    folder = new File(parent, file);
    File[] contents;
    if (folder.exists() && (contents = folder.listFiles()) != null) {
      for (File child : contents) {
        FileReader reader = new FileReader(child);
        add(gson.fromJson(reader, TradeLog.class));
        reader.close();
      }
    }
  }

  public Logs(File parent) throws IOException {
    this(parent, folderNameFormat.format(new Date()));
  }

  public void log(TradeLog log) {
    logs.add(log);
  }

  public void save() {
    if (!logs.isEmpty()) {
      if (!folder.exists()) folder.mkdirs();
      Iterator<TradeLog> iter = iterator();
      while (iter.hasNext()) {
        TradeLog log = iter.next();
        try {
          File file = new File(folder, fileNameFormat.format(log.getTime())
                                           .replace("{player1}", log.getPlayer1().getLastKnownName())
                                           .replace("{player2}", log.getPlayer2().getLastKnownName()));
          if (!file.exists()) file.createNewFile();
          FileWriter writer = new FileWriter(file);
          gson.toJson(log, TradeLog.class, writer);
          writer.close();
        } catch (IOException ex) {
          System.out.println("Failed to save trade log for trade between " + log.getPlayer1().getLastKnownName() + " and " + log.getPlayer2().getLastKnownName());
          System.out.println(ex.getLocalizedMessage());
        }
        iter.remove();
      }
    }
  }

  @Override public int size() {
    return logs.size();
  }

  @Override public boolean isEmpty() {
    return logs.isEmpty();
  }

  @Override public boolean contains(Object o) {
    return logs.contains(o);
  }

  @Override public Iterator<TradeLog> iterator() {
    return logs.iterator();
  }

  @Override public Object[] toArray() {
    return logs.toArray();
  }

  @Override public <T> T[] toArray(T[] a) {
    return logs.toArray(a);
  }

  @Override public boolean add(TradeLog tradeLog) {
    return logs.add(tradeLog);
  }

  @Override public boolean remove(Object o) {
    return logs.remove(o);
  }

  @Override public boolean containsAll(Collection<?> c) {
    return logs.containsAll(c);
  }

  @Override public boolean addAll(Collection<? extends TradeLog> c) {
    return logs.addAll(c);
  }

  @Override public boolean addAll(int index, Collection<? extends TradeLog> c) {
    return logs.addAll(index, c);
  }

  @Override public boolean removeAll(Collection<?> c) {
    return logs.removeAll(c);
  }

  @Override public boolean retainAll(Collection<?> c) {
    return logs.retainAll(c);
  }

  @Override public void replaceAll(UnaryOperator<TradeLog> operator) {
    logs.replaceAll(operator);
  }

  @Override public void sort(Comparator<? super TradeLog> c) {
    logs.sort(c);
  }

  @Override public void clear() {
    logs.clear();
  }

  @Override public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    return logs.equals(o);
  }

  @Override public int hashCode() {
    return logs.hashCode();
  }

  @Override public TradeLog get(int index) {
    return logs.get(index);
  }

  @Override public TradeLog set(int index, TradeLog element) {
    return logs.set(index, element);
  }

  @Override public void add(int index, TradeLog element) {
    logs.add(index, element);
  }

  @Override public TradeLog remove(int index) {
    return logs.remove(index);
  }

  @Override public int indexOf(Object o) {
    return logs.indexOf(o);
  }

  @Override public int lastIndexOf(Object o) {
    return logs.lastIndexOf(o);
  }

  @Override public ListIterator<TradeLog> listIterator() {
    return logs.listIterator();
  }

  @Override public ListIterator<TradeLog> listIterator(int index) {
    return logs.listIterator(index);
  }

  @Override public List<TradeLog> subList(int fromIndex, int toIndex) {
    return logs.subList(fromIndex, toIndex);
  }

  @Override public Spliterator<TradeLog> spliterator() {
    return logs.spliterator();
  }
}
