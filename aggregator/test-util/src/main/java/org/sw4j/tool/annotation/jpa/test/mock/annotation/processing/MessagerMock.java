/*
 * Copyright (C) 2016 uwe
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.sw4j.tool.annotation.jpa.test.mock.annotation.processing;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.processing.Messager;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;

/**
 * A Mock of the Messager for annotation processors.
 *
 * @author Uwe Plonus
 */
public class MessagerMock implements Messager {

    private final List<Message> messages;

    public MessagerMock() {
        messages = new LinkedList<>();
    }

    public List<Message> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    @Override
    public void printMessage(final Diagnostic.Kind kind, final CharSequence msg) {
        messages.add(new Message(kind, msg));
    }

    @Override
    public void printMessage(final Diagnostic.Kind kind, final CharSequence msg, final Element e) {
        messages.add(new Message(kind, msg, e));
    }

    @Override
    public void printMessage(final Diagnostic.Kind kind, final CharSequence msg, final Element e,
            final AnnotationMirror a) {
        messages.add(new Message(kind, msg, e, a));
    }

    @Override
    public void printMessage(final Diagnostic.Kind kind, final CharSequence msg, final Element e,
            final AnnotationMirror a, final AnnotationValue v) {
        messages.add(new Message(kind, msg, e, a, v));
    }


    public static class Message {

        private final Diagnostic.Kind kind;

        private final CharSequence msg;

        private final Element e;

        private final AnnotationMirror a;

        private final AnnotationValue v;

        public Message(final Diagnostic.Kind kind, final CharSequence msg) {
            this.kind = kind;
            this.msg = msg;
            this.e = null;
            this.a = null;
            this.v = null;
        }

        public Message(final Diagnostic.Kind kind, final CharSequence msg, final Element e) {
            this.kind = kind;
            this.msg = msg;
            this.e = e;
            this.a = null;
            this.v = null;
        }

        public Message(final Diagnostic.Kind kind, final CharSequence msg, final Element e, final AnnotationMirror a) {
            this.kind = kind;
            this.msg = msg;
            this.e = e;
            this.a = a;
            this.v = null;
        }

        public Message(final Diagnostic.Kind kind, final CharSequence msg, final Element e, final AnnotationMirror a,
                final AnnotationValue v) {
            this.kind = kind;
            this.msg = msg;
            this.e = e;
            this.a = a;
            this.v = v;
        }

        public Diagnostic.Kind getKind() {
            return kind;
        }

        public CharSequence getMsg() {
            return msg;
        }

        public Element getE() {
            return e;
        }

        public AnnotationMirror getA() {
            return a;
        }

        public AnnotationValue getV() {
            return v;
        }

    }

}
