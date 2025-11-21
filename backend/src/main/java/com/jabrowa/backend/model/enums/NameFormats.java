package com.jabrowa.backend.model.enums;

import com.jabrowa.backend.model.entities.Person;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <strong>NameFormats</strong> - Enumerator<br><br>
 * The enumerator specifies formatting functionality rather than just constants and attributes.
 * With this approach, long switches are avoided and the formatting itself is clearly defined by an enumerated
 * constant value. Each constant defines the functionality which formats the name components to the particular
 * presentation.
 * The options (enumerated constants) to format a name from its components are:
 * <ol>
 *     <li><strong>INFORMAL_FAMILY</strong></li>
 *     <i>[Nickname] [Prefixes family name] [Family name]</i>
 *     <li><strong>INFORMAL_MAIDEN</strong></li>
 *     <i>[Nickname] [Prefixes maiden name] [Maiden name]</i>
 *     <li><strong>INFORMAL_FAMILY_MAIDEN</strong></li>
 *     <i>[Nickname] [Prefixes family name] [Family name] [ - ] [Prefixes maiden name] [Maiden name]</i>
 *     <li><strong>INFORMAL_MAIDEN_FAMILY</strong></li>
 *     <i>[Nickname] [Prefixes maiden name] [Maiden name] [ - ] [Prefixes family name] [Family name]</i>
 *     <li><strong>FORMAL_FAMILY</strong></li>
 *     <i>[Titles prefix] [Initials] [Prefixes family name] [Family Name] [Titles suffix]</i>
 *     <li><strong>FORMAL_MAIDEN</strong></li>
 *     <i>[Titles prefix] [Initials] [Prefixes maiden name] [Maiden Name] [Titles suffix]</i>
 *     <li>><strong>FORMAL_FAMILY_MAIDEN</strong></li>
 *     <i>[Titles prefix] [Initials] [Prefixes family name] [Family Name] [ - ]
 *     [Prefixes maiden name] [Maiden Name] [Titles suffix]</i>
 *     <li>><strong>INFORMAL_MAIDEN_FAMILY</strong></li>
 *     <i>[Titles prefix] [Initials] [Prefixes maiden name] [Maiden Name] [ - ]
 *     [Prefixes family name] [Family Name] [Titles suffix]</i>
 *     <li><strong>TECHNICAL</strong></li>
 *     <i>[Family name / Maiden name] [, ] [Initials] [Prefixes family name / Prefixes maiden name] [ - ]
 *     [Prefixes maiden name] [maiden name] [(] [Nickname] [)]</i>
 * </ol>
 * <br>
 * To construct the name presentation according to the abovementioned options (and included business rules).
 * they are introduced to make it possible to specify and maintain the formatters (enumerated option) with just
 * simple pre-formatted name parts. The following (helper) methods are included in this enumerator:
 * <ul>
 *     <li><strong>formatName(<i>Person, boolean</i>)</strong></li>
 *     <i>Initiate the method 'formatName()' as integrated part of enumerator options. (like attributes)</li>
 *     <li><strong>hasFamilyName(<i>Person</i>)</strong></li>
 *     <i>Checks if the family name property of the person is specified.</i>
 *     <li><strong>hasMaidenName(<i>Person</i>)</strong></li>
 *     <i>Checks if the maiden name property of the person is specified.</i>
 *     <li><strong>hasSurname(<i>Person</i>)</strong></li>
 *     <i>Checks if either the family name property  or the maiden name property of the person or both
 *     is/are specified.</i>
 *     <li><strong>composeSurname(<i>Person</i>)</strong></li>
 *     <i>Composes the family name and maiden name to one in a requested order seperated by a hyphen if necessary.</i>
 *     <li><strong>familyPrefix(<i>Person</i>)</strong></li>
 *     <i>Provides the prefixes of the family name after checking if they are applicable and specified.</i>
 *     <li><strong>maidenPrefix(<i>Person</i>)</strong></li>
 *     <i>Provides the prefixes of the maiden name after checking if they are applicable and specified.</i>
 *     <li><strong>prefixTitlesIfAllowed(<i>Person, boolean</i>)</strong></li>
 *     <i>Provides the prefixed titles if requested and specified.</i>
 *     <li><strong>suffixTitlesIfAllowed(<i>Person, boolean</i>)</strong></li>
 *     <i>Provides the suffixed titles if requested and specified.</i>
 *     <li><strong>initialsIfAllowed(<i>Person</i>)</strong></li>
 *     <i>Provides the initials if required for the presentation and applicable.</i>
 *     <li><strong>nicknameInParentheses(<i>Person</i>)</strong></strong></li>
 *     <i>Provides if specified the nickname between parentheses.</i>
 * </ul>
 * <br>
 *  The following methods are use in the technical presentation(s) only:
 * <ul>
 *     <li><strong>primarySurname(<i>Person</i>)</strong></li>
 *     <i>Determines and provides the first name without the prefixes in the technical presentation.</i>
 *     <li><strong>secondarySurname(<i>Person</i>)</strong></li>
 *     <i>Determines and provides the second name part in the technical presentation, including the prefixes.</i>
 *     <li><strong>primaryPrefix(<i>Person</i>)</strong></li>
 *     <i>Provides the prefixes associated with the name which is used as first part of the technical format,
 *     if specified.</i>
 *     <li><strong>joinNameComponents(<i>String...</i>)</strong></li>
 *     <i>Determines and provides the prefixes associated with the name which is used as second part of the technical
 *     format, if specified.</i>
 * </ul>
 */

public enum NameFormats {
    /* [Nickname] [Prefixes family name] [Family name] */
    INFORMAL_FAMILY {
        @Override
        public String formatName(Person person, boolean includeTitles) {
            return joinNameComponents(
                    prefixTitlesIfAllowed(person, includeTitles),
                    person.getNickname(),
                    familyPrefix(person),
                    person.getFamilyName(),
                    suffixTitlesIfAllowed(person, includeTitles)
            );
        }
    },
    /* [Nickname] [Prefixes maiden name] [Maiden name] */
    INFORMAL_MAIDEN {
        @Override
        public String formatName(Person person, boolean includeTitles) {
            return joinNameComponents(
                    prefixTitlesIfAllowed(person, includeTitles),
                    person.getNickname(),
                    maidenPrefix(person),
                    person.getMaidenName(),
                    suffixTitlesIfAllowed(person, includeTitles)
            );
        }
    },
    /* [Nickname] [Prefixes family name] [Family name] [ - ] [Prefixes maiden name] [Maiden name] */
    INFORMAL_FAMILY_MAIDEN {
        @Override
        public String formatName(Person person, boolean includeTitles) {
            return joinNameComponents(
                    prefixTitlesIfAllowed(person, includeTitles),
                    person.getNickname(),
                    composeSurname(person, 0),
                    suffixTitlesIfAllowed(person, includeTitles)
            );
        }
    },
    /* [Nickname] [Prefixes maiden name] [Maiden name] [ - ] [Prefixes family name] [Family name] */
    INFORMAL_MAIDEN_FAMILY {
        @Override
        public String formatName(Person person, boolean includeTitles) {
            return joinNameComponents(
                    prefixTitlesIfAllowed(person, includeTitles),
                    person.getNickname(),
                    composeSurname(person, 1),
                    suffixTitlesIfAllowed(person, includeTitles)
            );
        }
    },
    /* [Titles prefix] [Initials] [Prefixes family name] [Family Name] [Titles suffix] */
    FORMAL_FAMILY {
        @Override
        public String formatName(Person person, boolean includeTitles) {
            return joinNameComponents(
                    prefixTitlesIfAllowed(person, includeTitles),
                    initialsIfAllowed(person),
                    familyPrefix(person),
                    person.getFamilyName(),
                    suffixTitlesIfAllowed(person, includeTitles)
            );
        }
    },
    /* Titles, Initials, Prefixes maiden name, Maiden name */
    FORMAL_MAIDEN {
        @Override
        public String formatName(Person person, boolean includeTitles) {
            return joinNameComponents(
                    prefixTitlesIfAllowed(person, includeTitles),
                    initialsIfAllowed(person),
                    maidenPrefix(person),
                    person.getMaidenName(),
                    suffixTitlesIfAllowed(person, includeTitles)
            );
        }
    },
    /* Titles, Initials, Prefixes family name, Family Name, Hyphen, Prefixes maiden name, Maiden name */
    FORMAL_FAMILY_MAIDEN {
        @Override
        public String formatName(Person person, boolean includeTitles) {
            return joinNameComponents(
                    prefixTitlesIfAllowed(person, includeTitles),
                    initialsIfAllowed(person),
                    composeSurname(person, 0),
                    suffixTitlesIfAllowed(person, includeTitles)
            );
        }
    },
    /* Titles, Initials, Prefixes maiden name, Maiden name, Hyphen, Prefixes family name, Family Name */
    FORMAL_MAIDEN_FAMILY {
        @Override
        public String formatName(Person person, boolean includeTitles) {
            return joinNameComponents(
                    prefixTitlesIfAllowed(person, includeTitles),
                    initialsIfAllowed(person),
                    composeSurname(person, 1),
                    suffixTitlesIfAllowed(person, includeTitles)
            );
        }
    },

    /*
    [Family name / Maiden name] [, ] [Initials] [Prefixes family name / Prefixes maiden name] [ - ]
    [Prefixes maiden name] [maiden name] [(] [Nickname] [)]
    */
    TECHNICAL {
        @Override
        public String formatName(Person person, boolean includeTitles) {
            return joinNameComponents(
                    primarySurname(person),
                    secondarySurname(person),
                    initialsIfAllowed(person),
                    primaryPrefix(person),
                    nicknameInParenthesis(person)
            );
        }
    };

    /**
     * <strong>formatName(<i>Person, boolean</i>)</strong><br><br>
     * Defines the method 'formatName()' as integrated part of enumerator options. (Pretty much like attributes)
     * @param person An instance of any class which has extended the Person class (e.g. client)
     * @param includeTitles A boolean which indicates if the titles (prefixes and suffixes) are included in the
     *                      formatted name.
     * @return The formated string according to the given enumerator constant and the specified name components of
     * the given class which extended Person.
     */
    public abstract String formatName(Person person, boolean includeTitles);

    /**
     * <strong>hasFamilyName(<i>Person</i>)</strong><br><br>
     * Checks if the family name is specified in the given class instance.
     * @param person An instance of any class which has extended the Person class (e.g. client)
     * @return True if the name is specified in the (class instance's) family name field, otherwise false is returned.
     */
    private static boolean hasFamilyName(Person person) {
        return person.getFamilyName() != null && !person.getFamilyName().isBlank();
    }

    /**
     * <strong>hasMaidenName(<i>Person</i>)</strong><br><br>
     * Checks if the maiden name is specified in the given class instance.
     * @param person An instance of any class which has extended the Person class (e.g. client)
     * @return True if the name is specified in the (class instance's) maiden name field, otherwise false is returned.
     */
    private static boolean hasMaidenName(Person person) {
        return person.getMaidenName() != null && !person.getMaidenName().isBlank();
    }

    /**
     * <strong>hasSurname(<i>Person</i>)</strong><br><br>
     * Checks if any name components (family- or maiden name) is specified in the given instance.
     * @param person An instance of any class which has extended the Person class (e.g. client)
     * @return A boolean indicating is the instance contains any of the surnames (family- or maiden),
     * true if it does, false otherwise.
     */
    private static boolean hasSurname(Person person) {
        return hasFamilyName(person) || hasMaidenName(person);
    }

    /**
     * <strong>composeSurname(<i>Person</i>)</strong><br><br>
     * The surname, as a composition of family- and maiden name, is subjected to the following rules:
     * <ol>
     *     <li>When none of the name components (maiden- or family) is specified, the formatted name is empty.</li>
     *     <li>When only the maiden name component is specified, the maiden name is applied.</li>
     *     <li>When only the family name component is specified, the family name is applied</li>
     *     <li>When both name components are specified, both are applied in the requested order and are seperated by
     *     a hyphen.</li>
     * </ol>
     * These rules are implemented by this (helper) method.
     * @param person An instance of any class which has extended the Person class (e.g. client)
     * @param order Specifies if either the family name component is mentioned first (0),
     *              or the maiden name component (1 or any other value).
     * @return The composed and formatted surname, compliant with the abovementioned rules'.
     * <i>null</i> is returned when no surname component is specified instead of an empty string.
     */
    private static String composeSurname(Person person, int order) {
        String returnName = "";
        if (hasMaidenName(person) && hasFamilyName(person)) {
            if (order == 0) {
                returnName = joinNameComponents(
                        familyPrefix(person),
                        person.getFamilyName(),
                        "-",
                        maidenPrefix(person),
                        person.getMaidenName());
            } else {
                returnName = joinNameComponents(
                        maidenPrefix(person),
                        person.getMaidenName(),
                        "-",
                        familyPrefix(person),
                        person.getFamilyName());
            }
        }
        if (!returnName.isBlank()) return returnName;
        if (hasMaidenName(person)) return person.getMaidenName();
        if (hasFamilyName(person)) return person.getFamilyName();
        return null;
    }

    /**
     * <strong>familyPrefix(<i>Person</i>)</strong><br><br>
     * Checks if the prefixes associated with the family name are applicable and specified.
     * Applicable means that the family name component of the given Person's instance is specified.
     * @param person An instance of any class which has extended the Person class (e.g. client)
     * @return The prefixes associated with the family name when specified in the given instance of the person.
     * Returns <i>null</i> if not specified or the associated family name component is not specified.
     */
    private static String familyPrefix(Person person) {
        return hasFamilyName(person) ? person.getPrefixesFamilyName() : null;
    }

    /**
     * <strong>maidenPrefix(<i>Person</i>)</strong><br><br>
     * Checks if the prefixes associated with the maiden name are applicable and specified.
     * Applicable means that the maiden name component of the given Person's instance is specified.
     * @param person An instance of any class which has extended the Person class (e.g. client)
     * @return The prefixes associated with the maiden name when specified in the given instance of the person.
     * Returns <i>null</i> if not specified or the associated maiden name component is not specified.
     */
    private static String maidenPrefix(Person person) {
        return hasMaidenName(person) ? person.getPrefixesMaidenName() : null;
    }

    /**
     * <strong>prefixTitlesIfAllowed(<i>Person, boolean</i>)</strong><br><br>
     * Implementation of the rule that a title prefix is only included in the formatted name if at least one of the
     * surname components (family- or maiden name) is specified and the inclusion is requested.
     * @param person An instance of any class which has extended the Person class (e.g. client)
     * @param includeTitle A boolean indicating if inclusion of the prefixed titles are requested
     * @return The prefixed titles if they are requested and are specified, otherwise <i>null</i> is returned.
     */
    private static String prefixTitlesIfAllowed(Person person, boolean includeTitle) {
        return includeTitle && hasSurname(person) ? person.getPrefixTitles() : null;
    }

    /**
     * <strong>suffixTitlesIfAllowed(<i>Person, boolean</i>)</strong><br><br>
     * Implementation of the rule that a title suffix is only included in the formatted name if at least one of the
     * surname components (family- or maiden name) is specified and the inclusion is requested.
     * @param person An instance of any class which has extended the Person class (e.g. client)
     * @param includeTitle A boolean indicating if inclusion of the suffixed titles are requested
     * @return The suffixed titles if they are requested and are specified, otherwise <i>null</i> is returned.
     */
    private static String suffixTitlesIfAllowed(Person person, boolean includeTitle) {
        return includeTitle && hasSurname(person) ? person.getSuffixTitles() : null;
    }

    /**
     * <strong>initialsIfAllowed(<i>Person</i>)</strong><br><br>
     * Checks if the initials are specified and applicable. The initials are applicable when at least one of the
     * name components of the given Person's instance (family- or maiden name) is specified.
     * @param person An instance of any class which has extended the Person class (e.g. client)
     * @return The specified initials, but only when at least one of the name components (family- or maiden name) is
     * specified, otherwise, <i>null</i> is returned.
     */
    private static String initialsIfAllowed(Person person) {
        return hasSurname(person) ? person.getInitials() : null;
    }

    /**
     * <strong>nicknameInParens(<i>Person</i>)</strong><br><br>
     * Composes the nickname within parentheses when applicable, which means when the nickname is specified.
     * @param person An instance of any class which has extended the Person class (e.g. client)
     * @return The nickname within parentheses. When the nickname isn't specified, <i>null</i> is returned.
     */
    private static String nicknameInParenthesis(Person person) {
        if (person.getNickname() == null || person.getNickname().isBlank())
            return null;
        return "(" + person.getNickname().trim() + ")";
    }

    /**
     * <strong>primarySurname(<i>Person</i>)</strong><br><br>
     * Checks if one of the name components (family- or maiden name) is specified and returns one of them with the
     * family name as the primary (first) one.
     * @param person An instance of any class which has extended the Person class (e.g. client or practitioner)
     * @return The family name component if specified otherwise the maiden name component.
     * When none of the name components is specified, <i>null</i> is returned.
     */
    protected static String primarySurname(Person person) {
        String returnName = "";
        String comma = ", ";
        if (person.getFamilyName() != null && !person.getFamilyName().isBlank())
            returnName =  person.getFamilyName() + comma;
        else if (person.getMaidenName() != null && !person.getMaidenName().isBlank())
            returnName = person.getMaidenName() + comma;

        if (!(returnName.isBlank())) return returnName;
        return null;
    }

    /**
     * <strong>secondarySurname(<i>Person</i>)</strong><br><br>
     * Checks if the primary surname is specified and when it is selects the maiden name secondary one.
     * @param person An instance of any class which has extended the Person class (e.g. practitioner)
     * @return The maiden name including its prefixes if the primary surname is the family name.
     */
    protected static String secondarySurname(Person person) {
        if (person.getFamilyName() != null && !person.getFamilyName().isBlank()
                && person.getMaidenName() != null && !person.getMaidenName().isBlank()) {
            if ((person.getFamilyName()+", ").equals(primarySurname(person)))
                return joinNameComponents(
                        person.getPrefixesMaidenName(),
                        person.getMaidenName());
        }
        return null;
    }

    /**
     * <strong>primaryPrefix(<i>Person</i>)</strong><br><br>
     * Determines the prefix of the name component which is used as primary part of the technical formatted name.
     * @param person An instance of any class which has extended the Person class (e.g. client)
     * @return The prefix associated with the first used name component in the technical formatted name.
     * When no surname is specified, then <i>null</i> is returned.
     */
    protected static String primaryPrefix(Person person) {
        if (hasSurname(person)) {
            if (Objects.equals(primarySurname(person), person.getFamilyName() + ", ")) {
                return person.getPrefixesFamilyName();
            }
            if (Objects.equals(primarySurname(person), person.getMaidenName() + ", ")) {
                return person.getPrefixesMaidenName();
            }
        }
        return null;
    }

    /**
     * <strong>joinNameComponents(<i>String...</i>)</strong><br><br>
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
