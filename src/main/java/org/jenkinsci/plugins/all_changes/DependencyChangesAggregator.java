/*
 * The MIT License
 *
 * Copyright (c) 2011, Stefan Wolf
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.jenkinsci.plugins.all_changes;

import com.google.common.collect.ImmutableList;
import hudson.Extension;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;

import java.util.Collection;
import java.util.Map;

/**
 * @author wolfs
 */
@Extension
public class DependencyChangesAggregator extends ChangesAggregator {
    @Override
    public Collection<AbstractBuild> aggregateBuildsWithChanges(AbstractBuild build) {
        ImmutableList.Builder<AbstractBuild> builder = ImmutableList.<AbstractBuild>builder();
        Map<AbstractProject, AbstractBuild.DependencyChange> depChanges = build.getDependencyChanges((AbstractBuild) build.getPreviousBuild());
        for (AbstractBuild.DependencyChange depChange : depChanges.values()) {
            builder.addAll(depChange.getBuilds());
        }
        return builder.build();
    }
}
