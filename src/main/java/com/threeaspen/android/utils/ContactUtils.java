package com.threeaspen.android.utils;

import android.content.res.XmlResourceParser;
import android.location.Address;
import java.io.IOException;
import java.util.Collection;
import java.util.Locale;
import java.util.TreeMap;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParserException;

public class ContactUtils {
    public class Location {
        private String id;
        private String label;
        private TreeMap<String, Location> children;

        public Location(String id)
        {
            this.id = id;
        }

        public Location(String id, String label) {
            this.id = id;
            this.label = label;
        }

        public String getId()
        {
            return id;
        }

        public String getLabel()
        {
            return label;
        }

        void addChild(String id, String label)
        {
            children.put(id, new Location(id, label));
        }

        public Collection<Location> getChildren()
        {
            return children.values();
        }

    }

    private static Pattern ZIP = Pattern.compile("^\\d{5}$");
    private static Pattern ZIP_PLUS4 = Pattern.compile("^\\d{5}-\\d{4}$");

    /*public static Location parseLocations(XmlResourceParser xml)
            throws IOException, XmlPullParserException
    {
        Location root;
        int elementType = xml.getEventType();
        while (elementType != XmlResourceParser.END_DOCUMENT) {
            if (parentName.equals(xml.getName()) && parentId.equals(xml.getIdAttribute())) {
                if (elementType == XmlResourceParser.START_TAG) {
                    location = new Location();
                } else {
                    break;
                }
            } else if (items != null && elementType == XmlResourceParser.START_TAG && childName.equals(xml.getName())) {
                items.add(new Item(xml.getIdAttribute(), xml.getAttributeValue(null, "label")));
            }
            elementType = xml.next();
        }
    }*/

    public static Address parseGmailAddress(String address)
    {
        Address addr = new Address(Locale.getDefault());
        StringBuilder addressLine = new StringBuilder();
        String[] lineSplit = address.split("[\\n\\r]+");
        for (int l=lineSplit.length; l>=0; l--) {
            String[] spcSplit = lineSplit[l].split("\\s+");
            for (int s=spcSplit.length; s>=0; s--) {
                String spcTok = spcSplit[s];
                if (addr.getPostalCode() == null && isPostalCode(spcTok)) {
                    addr.setPostalCode(spcTok);
                } else if (addr.getAdminArea() == null && isAdminArea(spcTok)) {
                    addr.setAdminArea(spcTok);
                } else if (addr.getCountryName() == null && isCountryName(spcTok)) {
                    addr.setCountryName(spcTok);
                } else if (addr.getCountryCode() == null && isCountryCode(spcTok)) {
                    addr.setCountryCode(spcTok);
                } else if (addr.getLocality() == null) {
                    addr.setLocality(spcTok);
                } else {
                    addressLine.insert(0, spcTok);
                }
            }
        }
        addr.setAddressLine(0, addressLine.toString());
        return addr;
    }

    public static boolean isPostalCode(String spcTok)
    {
        if (ZIP.matcher(spcTok).matches()) return true;
        if (ZIP_PLUS4.matcher(spcTok).matches()) return true;
        return false;
    }

    public static boolean isAdminArea(String spcTok)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static boolean isCountryCode(String spcTok)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static boolean isCountryName(String spcTok)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static String numberToKey(String phoneNumber)
    {
        StringBuilder key = new StringBuilder();
        for (char c : phoneNumber.toCharArray()) {
            key.insert(0, c);
        }
        return key.toString();
    }

}
