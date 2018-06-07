package com.spr.flux.methods.jobs.imports.importFromRL;

import java.io.File;

import com.documentum.bpm.IDfWorkitemEx;
import com.documentum.fc.common.DfException;
import com.spr.flux.common.dm.logic.BookFmBm;
import com.spr.flux.common.dm.logic.Chapter;
import com.spr.flux.common.dm.logic.Edition;
import com.spr.flux.common.dm.someattrvalues.BookFmBmType;

/**
 * RL job utils.
 * 
 * @author Pavel Ilinskiy
 */
public final class RLUtils {

    /**
     * Private constructor.
     */
    private RLUtils() {};

    /**
     * Check if project complete.
     * 
     * @param workSet
     *            project work set.
     * @return true if complete.
     * @throws DfException
     */
    public static boolean checkProjectWorkSetComplete(final ProjectWorkSet workSet)
            throws DfException {
        final Edition edition = workSet.getProject().getEdition();
        for (final Chapter chapter : edition.getChapters()) {
            if (!isChapterLinked(chapter)) {
                return false;
            }
        }
        final BookFmBm bookFmBm = edition.getBookmatter(BookFmBmType.BOOK_BACK_MATTER);

        return null == bookFmBm || isBookFmBmLinked(bookFmBm, workSet);
    }

    /**
     * @param chapter
     *            chapter.
     * @return Chapter temp dir.
     */
    public static File getChapterTempDir(final Chapter chapter) {
        // TODO: make cache
        return new File(chapter.getString(Chapter.TEMPORARY_DIRECTORY));
    }

    /**
     * @param workSet
     *            Work set.
     * @return Workflow temp dir.
     * @throws DfException
     */
    public static File getWfTempDir(final WorkSet workSet) throws DfException {
        return new File(((IDfWorkitemEx) workSet.getWorkitem()).getPrimitiveVariableValue(
                TEMPORARY_DIR).toString());
    }

    /**
     * @param workSet
     *            work set.
     * @return Bookmatter dir.
     * @throws DfException
     */
    public static File getBookFmBmDir(final ProjectWorkSet workSet) throws DfException {
        return new File(getWfTempDir(workSet), workSet
                .getProject()
                .getElectronicRendition()
                .getObjectName());
    }

    /**
     * Check if chapter is already in place.
     * 
     * @param chapter
     *            Chapter to check.
     * @return true if chapter on place.
     * @throws DfException
     */
    private static boolean isChapterLinked(final Chapter chapter) throws DfException {
        final File chapterFile = new File(getChapterTempDir(chapter), chapter.getObjectName()
                + Chapter.CHAPTER_FILE_EXTENSION);
        return chapter.isAPageObject() || chapterFile.exists();
    }

    /**
     * Check if bookmatter is in place.
     * 
     * @param bookFmBm
     *            bookmatter to check.
     * @param workSet
     *            work set.
     * @return true if bookmatter is in place.
     * @throws DfException
     */
    private static boolean isBookFmBmLinked(final BookFmBm bookFmBm, final ProjectWorkSet workSet)
            throws DfException {
        final File bookFmBmFile = new File(getBookFmBmDir(workSet), bookFmBm.getObjectName()
                .replace("_XML", "_OnlinePDF")
                + ".xml");
        return bookFmBmFile.exists();
    }

    /** Process variable name for temporary folder. */
    private static final String TEMPORARY_DIR = "temporary_dir";
}
