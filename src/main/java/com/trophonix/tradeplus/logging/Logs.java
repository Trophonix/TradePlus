package com.trophonix.tradeplus.logging;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.UnaryOperator;

public class Logs implements List<TradeLog> {

  private static final Type logListType = new TypeToken<List<TradeLog>>(){ }.getType();

  private static final DateFormat fileNameFormat = new SimpleDateFormat(
      "'session_'yyyy-MM-dd'_'HH:mm:ss'.json'");

  private File file;
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
        .create();

  public Logs(File parent, String file) throws IOException {
    if (!parent.exists()) parent.mkdirs();
    this.file = new File(parent, file);
    if (this.file.exists()) {
      FileReader reader = new FileReader(this.file);
      logs.addAll(gson.fromJson(reader, logListType));
      reader.close();
    }
  }

  public Logs(File parent) throws IOException {
    this(parent, fileNameFormat.format(new Date()));
  }

  public void log(TradeLog log) {
    logs.add(log);
  }

  public void save() throws IOException {
    if (!logs.isEmpty()) {
      FileWriter writer = new FileWriter(file);
      gson.toJson(logs, logListType, writer);
      writer.close();
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
    if (o == null || getClass() != o.getClass()) return false;
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
