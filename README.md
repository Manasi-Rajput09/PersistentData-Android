\# Git Commands Cheat Sheet



\## Basic Workflow

git status        # check changes

git add .         # stage all files

git commit -m "message"   # save changes

git push          # upload to GitHub



\## Setup Identity

git config --global user.name "your-name"

git config --global user.email "your-email"



\## Fix Commit Author

git commit --amend --reset-author --no-edit

git push --force



\## Branching

git branch        # list branches

git checkout -b new-branch   # create branch



\## Remote

git remote -v     # check repo link







\--------------------------------------------

\# 1. Stage ALL changes (modified + deleted + new files)

**git add .**



\# 2. Save changes with a message

**git commit -m "Updated UI and project changes"**



\# 3. Push to GitHub

**git push**



\---------------------------------------------

***Change Author and Commiter name***



\# Set your Git username (author name)

**git config --global user.name "Manasi-Rajput09"**



\# Set your Git email (must match your GitHub email)

**git config --global user.email "your-email@example.com"**



\# Check current Git configuration

**git config --global --list**



\# Fix last commit (update author + committer)

**git commit --amend --reset-author --no-edit**



\# Force push changes to GitHub (overwrite old commit)

**git push --force**



\------------------------------------

*Current commiter*

**git log --oneline --decorate --show-signature --show-notes --pretty=full**



\-----------------------------------------------------

*Go back one commit*

*(doesn't work if only one commit)*



Step 1: Reset commit but keep files

**git reset --soft HEAD\~1**



👉 This removes the commit but keeps all your code



Step 2: Make sure Git is using your account



Set your correct identity:



git config user.name "Manasi-Rajput09"

git config user.email "your-email@example.com"



⚠️ Use the same email as your GitHub account



Step 3: Commit again

**git commit -m "Initial commit"**

Step 4: Force push (important)

**git push --force**



👉 This replaces the old commit on GitHub



