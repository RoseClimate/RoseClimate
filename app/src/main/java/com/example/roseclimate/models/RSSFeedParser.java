package com.example.roseclimate.models;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

import com.example.roseclimate.models.Feed;
import com.example.roseclimate.models.FeedItem;

public class RSSFeedParser {
    static final String TITLE = "title";
    static final String DESCRIPTION = "description";
    static final String CHANNEL = "channel";
    static final String LANGUAGE = "language";
    static final String COPYRIGHT = "copyright";
    static final String LINK = "link";
    static final String AUTHOR = "author";
    static final String ITEM = "item";
    static final String PUB_DATE = "pubDate";
    static final String GUID = "guid";
    private boolean dumbassHack = false;
    final URL url;

    public RSSFeedParser(String feedUrl) {
        try {
            this.url = new URL(feedUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public Feed readFeed() {
        Feed feed = null;
        try {
            boolean isFeedHeader = true;
            // Set header values initial to the empty string
            String description = "";
            String title = "";
            String link = "";
            String language = "";
            String copyright = "";
            String author = "";
            String pubdate = "";
            String guid = "";

            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            CompletableFuture<InputStream> completableFuture
                    = CompletableFuture.supplyAsync(() -> read());
            InputStream in;
            XMLEventReader eventReader = null;
            try {
                in = completableFuture.get();
                CompletableFuture<XMLEventReader> completableFuture2
                        = CompletableFuture.supplyAsync(() -> {
                    try {
                        return inputFactory.createXMLEventReader(in);
                    } catch (XMLStreamException e) {
                        e.printStackTrace();
                    }
                    return null;
                });

                try {
                    eventReader = completableFuture2.get();

                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }


            // read the XML document
            while (eventReader.hasNext()) {
                XMLEvent event = null;
                XMLEventReader finalEventReader1 = eventReader;
                CompletableFuture<XMLEvent> completableFuture4
                        = CompletableFuture.supplyAsync(() -> {
                    try {
                        return finalEventReader1.nextEvent();
                    } catch (XMLStreamException e) {
                        e.printStackTrace();
                    }
                    return null;
                });
                try {
                    event = completableFuture4.get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                if (event.isStartElement()) {

                    String localPart = event.asStartElement().getName()
                            .getLocalPart();
//                    if (dumbassHack){
//                        link = localPart;
//                        dumbassHack = false;
//                    }
                    switch (localPart) {
                        case ITEM:
                            if (isFeedHeader) {
                                isFeedHeader = false;
                                feed = new Feed(title, link, description, language,
                                        copyright, pubdate);
                            }
                            event = null;
                            XMLEventReader finalEventReader = eventReader;
                            CompletableFuture<XMLEvent> completableFuture3
                                    = CompletableFuture.supplyAsync(() -> {
                                try {
                                    return finalEventReader.nextEvent();
                                } catch (XMLStreamException e) {
                                    e.printStackTrace();
                                }
                                return null;
                            });
                            try {
                                event = completableFuture3.get();
                            } catch (ExecutionException | InterruptedException e) {
                                e.printStackTrace();
                            }
                            break;
                        case TITLE:
                            title = getCharacterData(event, eventReader);
                            break;
                        case DESCRIPTION:
                            description = getCharacterData(event, eventReader);
                            break;
                        case LINK:
                            link = getCharacterData(event, eventReader);
                            if (link.equals("")){
                                dumbassHack = true;
                            }
                            break;
                        case GUID:
                            guid = getCharacterData(event, eventReader);
                            break;
                        case LANGUAGE:
                            language = getCharacterData(event, eventReader);
                            break;
                        case AUTHOR:
                            author = getCharacterData(event, eventReader);
                            break;
                        case PUB_DATE:
                            pubdate = getCharacterData(event, eventReader);
                            break;
                        case COPYRIGHT:
                            copyright = getCharacterData(event, eventReader);
                            break;
                    }
                } else if (event.isEndElement()) {
                    if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
                        FeedItem item = new FeedItem();
                        item.setAuthor(author);
                        item.setDescription(description);
                        item.setGuid(guid);
                        item.setLink(link);
                        item.setTitle(title);
                        item.setPubDate(pubdate);
                        feed.getItems().add(item);
                        event = null;
                        XMLEventReader finalEventReader2 = eventReader;
                        CompletableFuture<XMLEvent> completableFuture5
                                = CompletableFuture.supplyAsync(() -> {
                            try {
                                return finalEventReader2.nextEvent();
                            } catch (XMLStreamException e) {
                                e.printStackTrace();
                            }
                            return null;
                        });
                        try {
                            event = completableFuture5.get();
                        } catch (ExecutionException | InterruptedException e) {
                            e.printStackTrace();
                        }
                        continue;
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }


        return feed;
    }

    private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        CompletableFuture<XMLEvent> completableFuture
                = CompletableFuture.supplyAsync(() -> {
            try {
                return eventReader.nextEvent();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
            return null;
        });
        try {
            event = completableFuture.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }

    private InputStream read() {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
//