package com.jabrowa.backend.model.enums;

import com.jabrowa.backend.model.entities.Person;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public enum NameFormats {

    /* Nickname, prefixes and family name */
    INFORMAL_FAMILY {
        @Override
        public String formatName(Person person, boolean includeTitles) {
            return NameFormats.joinNameComponents(
                    person.getNickname(), person.getPrefixesFamilyName(), person.getFamilyName()
            );
        }
    },

    INFORMAL_GIVEN {
        @Override
        public String formatName(Person person, boolean includeTitles) {
            return joinNameComponents(
                    person.getNickname(),
                    "(" + joinNameComponents(person.getPrefixesMaidenName(), person.getMaidenName()) + ")"
            );
        }
    },

    /* Titles, Initials, Prefixes family name, Family Name */
    FORMAL_FAMILY {
        @Override
        public String formatName(Person person, boolean includeTitles) {
            return joinNameComponents(
                    includeTitles && (person.getFamilyName() != null || person.getMaidenName() != null) ?
                            person.getPrefixTitles() : null,

                    joinNameComponents(
                            person.getFamilyName() != null ? person.getInitials() : null,
                            person.getFamilyName() != null ?
                                    person.getPrefixesFamilyName() : null, person.getFamilyName()
                    )
            );
        }
    },
    /* Titles, Initials, Prefixes maiden name, Maiden name */
    FORMAL_MAIDEN {
        @Override
        public String formatName(Person person, boolean includeTitles) {
            return joinNameComponents(
                    includeTitles && (person.getFamilyName() != null || person.getMaidenName() != null) ?
                            person.getPrefixTitles() : null,

                    joinNameComponents(
                            person.getMaidenName() != null ? person.getInitials() : null,

                            person.getMaidenName() != null ? person.getPrefixesMaidenName() : null,
                            person.getMaidenName())
            );
        }
    },
    /* Titles, Initials, Prefixes family name, Family Name, Hyphen, Prefixes maiden name, Maiden name */
    FORMAL_FAMILY_MAIDEN {
        @Override
        public String formatName(Person person, boolean includeTitles) {
            return joinNameComponents(
                    includeTitles && (person.getFamilyName() != null || person.getMaidenName() != null) ?
                            person.getPrefixTitles() : null,

                    joinNameComponents(
                            person.getFamilyName() != null || person.getMaidenName() != null ?
                                    person.getInitials() : null,

                            person.getFamilyName() != null ? person.getPrefixesFamilyName() : null,
                            person.getFamilyName(),

                            person.getFamilyName() != null && person.getMaidenName() != null ? " - " : null,

                            person.getMaidenName() != null ?
                                    person.getPrefixesMaidenName() : null, person.getMaidenName())
            );
        }
    },
    /* Titles, Initials, Prefixes maiden name, Maiden name, Hyphen, Prefixes family name, Family Name */
    FORMAL_MAIDEN_FAMILY {
        @Override
        public String formatName(Person person, boolean includeTitles) {
            return joinNameComponents(
                    includeTitles && (person.getFamilyName() != null || person.getMaidenName() != null) ?
                            person.getPrefixTitles() : null,

                    joinNameComponents(
                            person.getFamilyName() != null || person.getMaidenName() != null ?
                                    person.getInitials() : null,

                            person.getMaidenName() != null ?
                                    person.getPrefixesMaidenName() : null, person.getMaidenName(),

                            person.getFamilyName() != null && person.getMaidenName() != null ? " - " : null,

                            person.getFamilyName() != null ?
                                    person.getPrefixesFamilyName() : null, person.getFamilyName())
            );
        }
    },


    TECHNICAL_INFORMAL {
        @Override
        public String formatName(Person person, boolean includeTitles) {
            return joinNameComponents(person.getInitials(), person.getFamilyName());
        }
    },

    TECHNICAL_INFORMAL_WITH_GIVEN {
        @Override
        public String formatName(Person person, boolean includeTitles) {
            return joinNameComponents(
                    joinNameComponents(person.getPrefixesMaidenName(), person.getMaidenName()),
                    "(" + joinNameComponents(person.getInitials(), person.getFamilyName()) + ")"
            );
        }
    };

    /**
     * <strong>formatName(<i>Person, boolean</i>)</strong><br><br>
     * @param person An instance of any class which has extended the Person class (e.g. client)
     * @param includeTitles A boolean which indicates if the titles (prefixes and suffixes) are included in the
     *                      formatted name.
     * @return The formated string according to the given enumerator constant and the specified name components of
     * the given class which extended Person.
     */
    public abstract String formatName(Person person, boolean includeTitles);

    /**
     * <strong>join(<i>String...</i>)</strong><br><br>
     * Converts strings to clean space-separated concatenated ones. Removes double spaces and add single spaces between
     * the individual name components.
     * @param nameComponents The comma seperated components of string to compose.
     * @return A string of all components seperated by a single space in the order where they are passed.
     * */
    protected static String joinNameComponents(String... nameComponents) {
        return Arrays.stream(nameComponents)
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining(" "));
    }
}
