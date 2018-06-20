package com.academy;

import java.io.IOException;
import java.util.List;

interface UI {
    void draw(List<Flake> shapes) throws IOException, InterruptedException;
}
