# weaselware2
the granite shop database of the future

This is going to be a massive project.  The database will hold tables for material inventory and customer info but those
tables will have foreign keys effectively splitting the tables between different companies.

Users will be divided into "companies", "bosses", and "standards users".  So I could create a company profile and then add other
users to my profile.  Selected users could add events and items to our database tables and others will only be able to click off
tasks.

So if the inventory table contained 10,000 items but only 250 had a foriegn key for my compnay then I'd only see those 250.

I'll be adding Events, there might be hundreds or only a few types of events packaged with the software like call customer,
template, fab, install.  So if one standard user clicked off "call customer" it would spawn a "template" event in someone else's
profile.

My ideas are all over the place.  Right now I'm focused on just creating event tables, user signup, different levels of users,
and spawning different events depending on what's clicked off, then making it so a boss user can create new event types that link
to other event types.
