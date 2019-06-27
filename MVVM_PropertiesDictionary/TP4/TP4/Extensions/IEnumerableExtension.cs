using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TP4.Extensions
{
    public static class IEnumerableExtension
    {
        public static List<T> GetRandomizedList<T>(this IEnumerable<T> collection)
        {
            return collection.OrderBy(c => Guid.NewGuid()).ToList();
        }
    }
}
