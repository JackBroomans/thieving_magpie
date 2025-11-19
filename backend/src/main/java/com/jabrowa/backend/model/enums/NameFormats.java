package com.jabrowa.backend.model.enums;

import com.jabrowa.backend.model.entities.Person;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public enum NameFormats {

    /* Nickname, prefixes and family name */
    INFORMAL {
        @Override
        public String format(Person person, boolean includeTitles) {
            return NameFormats.join(
                    person.getNickname()
            );
        }
    },

    INFORMAL_FAMILY {
        @Override
        public String format(Person person, boolean includeTitles) {
            return NameFormats.join(
                    person.getNickname()
            );
        }
    },

    INFORMAL_WITH_GIVEN {
        @Override
        public String format(Person person, boolean includeTitles) {
            return join(
                    person.getNickname(),
                    "(" + join(person.getPrefixesMaidenName(), person.getMaidenName()) + ")"
            );
        }
    },

    /* Titles, Initials, Prefixes family name, Family Name */
    FORMAL_FAMILY {
        @Override
        public String format(Person person, boolean includeTitles) {
            return join(
                    includeTitles && (person.getFamilyName() != null || person.getMaidenName() != null) ?
                            person.getPrefixTitles() : null,
                    person.getFamilyName() != null ? person.getInitials() : null,
                    person.getFamilyName() != null ? person.getPrefixesFamilyName() : null, person.getFamilyName()
            );
        }
    },
    /* Titles, Initials, Prefixes maiden name, Maiden name */
    FORMAL_MAIDEN {
        @Override
        public String format(Person person, boolean includeTitles) {
            return join(
                    includeTitles && (person.getFamilyName() != null || person.getMaidenName() != null) ?
                            person.getPrefixTitles() : null,
                    person.getMaidenName() != null ? person.getInitials() : null,
                    person.getMaidenName() != null ? person.getPrefixesMaidenName() : null, person.getMaidenName()
            );
        }
    },
    /* Titles, Initials, Prefixes family name, Family Name, Hyphen, Prefixes maiden name, Maiden name */
    FORMAL_FAMILY_MAIDEN {
        @Override
        public String format(Person person, boolean includeTitles) {
            return join(
                    includeTitles && (person.getFamilyName() != null || person.getMaidenName() != null) ?
                            person.getPrefixTitles() : null,

                    (person.getFamilyName() != null || person.getMaidenName() != null) ? person.getInitials() : null,

                    join(person.getFamilyName() != null ? person.getPrefixesFamilyName() : null, person.getFamilyName(),
                            (person.getFamilyName() != null && person.getMaidenName() != null) ? " - " : null,
                            person.getMaidenName() != null ?
                                    person.getPrefixesMaidenName() : null, person.getMaidenName())
            );
        }
    },
    /* Titles, Initials, Prefixes maiden name, Maiden name, Hyphen, Prefixes family name, Family Name */
    FORMAL_MAIDEN_FAMILY {
        @Override
        public String format(Person person, boolean includeTitles) {
            return join(
                    includeTitles && (person.getFamilyName() != null || person.getMaidenName() != null) ?
                            person.getPrefixTitles() : null,

                    (person.getFamilyName() != null || person.getMaidenName() != null) ? person.getInitials() : null,

                    join(person.getMaidenName() != null ? person.getPrefixesMaidenName() : null, person.getMaidenName(),
                            (person.getFamilyName() != null && person.getMaidenName() != null) ? " - " : null,
                            person.getFamilyName() != null ?
                                    person.getPrefixesFamilyName() : null, person.getFamilyName())
            );
        }
    },


    TECHNICAL_INFORMAL {
        @Override
        public String format(Person person, boolean includeTitles) {
            return join(person.getInitials(), person.getFamilyName());
        }
    },

    TECHNICAL_INFORMAL_WITH_GIVEN {
        @Override
        public String format(Person person, boolean includeTitles) {
            return join(
                    join(person.getPrefixesMaidenName(), person.getMaidenName()),
                    "(" + join(person.getInitials(), person.getFamilyName()) + ")"
            );
        }
    };

    public abstract String format(Person person, boolean includeTitles);

    /** Helper for clean space-separated concatenation */
    protected static String join(String... nameComponents) {
        return Arrays.stream(nameComponents)
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining(" "));
    }
}
